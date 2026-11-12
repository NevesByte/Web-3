package br.com.estanteweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LivroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Livro> listarTodos() {

        String sql = "SELECT * FROM livro";

        List<Map<String, Object>> linhas =
                jdbcTemplate.queryForList(sql);

        return converterLinhas(linhas);
    }

    public Livro buscarPorId(Long id) {

        String sql = "SELECT * FROM livro WHERE id = ?";

        List<Map<String, Object>> linhas =
                jdbcTemplate.queryForList(sql, id);

        return linhas.isEmpty() ? null : Livro.deMap(linhas.get(0));
    }

    public List<Livro> listarOrdenadoPor(String coluna) {

        String colunaOrdenacao = switch (coluna == null ? "" : coluna) {
            case "titulo" -> "titulo";
            case "autor" -> "autor";
            case "ano" -> "ano";
            case "preco" -> "preco";
            default -> null;
        };

        if (colunaOrdenacao == null) {
            return listarTodos();
        }

        String sql = "SELECT * FROM livro ORDER BY " + colunaOrdenacao;
        return converterLinhas(jdbcTemplate.queryForList(sql));
    }

    public List<Livro> listarDoMesmoAutor(String autor, Long idAtual) {

        String sql = "SELECT * FROM livro WHERE autor = ? AND id <> ?";

        List<Map<String, Object>> linhas =
                jdbcTemplate.queryForList(sql, autor, idAtual);

        return converterLinhas(linhas);
    }

    private List<Livro> converterLinhas(List<Map<String, Object>> linhas) {

        List<Livro> livros = new ArrayList<>();

        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }

        return livros;
    }

    public int contarLivros() {

        String sql = "SELECT COUNT(*) FROM livro";

        return jdbcTemplate.queryForObject(
                sql,
                Integer.class
        );
    }

    public void salvar(Livro livro) {

        String sql = "INSERT INTO livro (titulo, autor, ano, preco) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno(),
                livro.getPreco()
        );
    }

    public List<Livro> listarAtePreco(double precoMaximo) {

        String sql = "SELECT * FROM livro WHERE preco <= ?";

        List<Map<String, Object>> linhas =
                jdbcTemplate.queryForList(
                        sql,
                        precoMaximo
                );

        List<Livro> livros = new ArrayList<>();

        for (Map<String, Object> linha : linhas) {
            livros.add(Livro.deMap(linha));
        }

        return livros;
    }

    public double somarValorAcervo() {

        String sql = "SELECT SUM(preco) FROM livro";

        Double resultado =
                jdbcTemplate.queryForObject(
                        sql,
                        Double.class
                );

        return resultado != null ? resultado : 0.0;
    }
}