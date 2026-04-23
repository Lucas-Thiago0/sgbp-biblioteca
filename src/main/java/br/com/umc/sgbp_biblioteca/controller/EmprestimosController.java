package br.com.umc.sgbp_biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimosController {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", java.util.Collections.emptyList());
        model.addAttribute("totalEmprestimos", 0);
        model.addAttribute("emprestimosAtivos", 0);
        model.addAttribute("emprestimosDevolvidos", 0);
        return "emprestimos";
    }
}