package br.com.umc.sgbp_biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservasController {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas",          java.util.Collections.emptyList());
        model.addAttribute("totalReservas",     0);
        model.addAttribute("reservasAtivas",    0);
        model.addAttribute("reservasExpiradas", 0);
        return "reservas";
    }

    @PostMapping
    public String salvar(@RequestParam(required = false) String nomeUsuario,
                         @RequestParam(required = false) String emailUsuario,
                         @RequestParam(required = false) String tituloLivro,
                         @RequestParam(required = false) String dataValidade) {
        return "redirect:/reservas";
    }

    @GetMapping("/confirmar/{id}")
    public String confirmar(@PathVariable String id) {
        return "redirect:/reservas";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable String id) {
        return "redirect:/reservas";
    }
}