package br.com.umc.sgbp_biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("atendimentos", java.util.Collections.emptyList());
        return "atendimento";
    }

    @PostMapping("/emprestar")
    public String emprestar(@RequestParam(required = false) String nomeUsuario,
                            @RequestParam(required = false) String emailUsuario,
                            @RequestParam(required = false) String tituloLivro,
                            @RequestParam(required = false) String dataDevolucaoPrevista) {
        return "redirect:/atendimento";
    }

    @PostMapping("/devolver")
    public String devolver(@RequestParam(required = false) String nomeUsuario,
                           @RequestParam(required = false) String tituloLivro,
                           @RequestParam(required = false) String estadoLivro,
                           @RequestParam(required = false) String observacao) {
        return "redirect:/atendimento";
    }

    @PostMapping("/ocorrencia")
    public String ocorrencia(@RequestParam(required = false) String nomeUsuario,
                             @RequestParam(required = false) String tituloLivro,
                             @RequestParam(required = false) String tipoOcorrencia,
                             @RequestParam(required = false) String descricao) {
        return "redirect:/atendimento";
    }
}