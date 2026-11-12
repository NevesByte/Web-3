package br.com.estanteweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class LivroController {

    @Autowired
    private LivroRepository repositorio;

    @GetMapping("/livros/novo")
    public String novoLivro() {

        return "livro-novo";
    }

    @GetMapping("/livros")
    @ResponseBody
    public List<Livro> listarLivros() {

        return repositorio.listarTodos();
    }

    @GetMapping("/livros/total")
    @ResponseBody
    public int contarLivros() {

        return repositorio.contarLivros();
    }

    @GetMapping("/livros/busca")
    @ResponseBody
    public List<Livro> buscarPorPreco(
            @RequestParam(
                    name = "precoMaximo",
                    defaultValue = "999999"
            )
            double precoMaximo
    ) {

        return repositorio.listarAtePreco(precoMaximo);
    }

    @GetMapping("/livros/html")
    public String livrosHtml(
            @RequestParam(required = false) String ordem,
            Model model
    ) {

        model.addAttribute(
                "livros",
                repositorio.listarOrdenadoPor(ordem)
        );

        return "livros";
    }

    @PostMapping("/livros")
    public String salvarLivro(
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam int ano,
            @RequestParam double preco,
            Model model
    ) {

        if (titulo.isBlank() || autor.isBlank() || preco <= 0) {
            model.addAttribute(
                    "erro",
                    "Título e autor são obrigatórios, e o preço deve ser maior que zero."
            );
            model.addAttribute("titulo", titulo);
            model.addAttribute("autor", autor);
            model.addAttribute("ano", ano);
            model.addAttribute("preco", preco);

            return "livro-novo";
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);
        repositorio.salvar(livro);

        return "redirect:/livros/html";
    }

    @GetMapping("/livros/{id}")
    public String livroPorId(@PathVariable Long id, Model model) {

        Livro livro = repositorio.buscarPorId(id);

        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("livro", livro);
        model.addAttribute(
                "outrosLivros",
                repositorio.listarDoMesmoAutor(livro.getAutor(), id)
        );

        return "livro";
    }

    @GetMapping("/livros/valor-total")
    @ResponseBody
    public double valorTotal() {

        return repositorio.somarValorAcervo();
    }
}