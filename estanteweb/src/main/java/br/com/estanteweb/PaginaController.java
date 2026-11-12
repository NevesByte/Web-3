package br.com.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaginaController {

    @GetMapping("/")
    @ResponseBody
    public String inicio() {
        return "Bem-vindo à EstanteWeb!";
    }

    @GetMapping("/sobre")
    @ResponseBody
    public String sobre() {
        return "EstanteWeb - uma biblioteca online.";
    }

    @GetMapping("/contato")
    public String contato(Model model) {

        return "contato";
    }

    @PostMapping("/contato")
    public String enviarContato(
            @RequestParam String nome,
            @RequestParam String mensagem,
            Model model
    ) {

        if (mensagem == null || mensagem.isBlank()) {

            model.addAttribute(
                    "erro",
                    "A mensagem não pode ficar em branco."
            );

            model.addAttribute(
                    "nome",
                    nome
            );

            return "contato";
        }

        return "redirect:/contato/sucesso";
    }

    @GetMapping("/contato/sucesso")
    public String contatoSucesso() {

        return "contato-sucesso";
    }
}