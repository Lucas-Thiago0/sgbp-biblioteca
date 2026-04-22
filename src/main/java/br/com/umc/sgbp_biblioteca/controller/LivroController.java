package br.com.umc.sgbp_biblioteca.controller;

import br.com.umc.sgbp_biblioteca.model.Livro;
import br.com.umc.sgbp_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", service.listarTodos());
        return "lista-livros";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "cadastro-livro";
    }

    @PostMapping
    public String salvar(@ModelAttribute("livro") Livro livro) {
        service.salvar(livro);
        return "redirect:/livros";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        service.buscarPorId(id).ifPresent(livro -> model.addAttribute("livro", livro));
        return "cadastro-livro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable String id) {
        service.excluir(id);
        return "redirect:/livros";
    }
}