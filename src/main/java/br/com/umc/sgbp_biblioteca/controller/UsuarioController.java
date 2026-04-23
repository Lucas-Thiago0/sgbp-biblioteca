package br.com.umc.sgbp_biblioteca.controller;

import br.com.umc.sgbp_biblioteca.model.Usuario;
import br.com.umc.sgbp_biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    public String listar(Model model) {
        List<Usuario> lista = service.listarTodos();
        model.addAttribute("usuarios", lista);

        // Fazemos as contas aqui no Java para o HTML não quebrar
        long ativos = lista.stream().filter(u -> u.isAtivo()).count();
        long bloqueados = lista.size() - ativos;

        model.addAttribute("totalUsuarios", lista.size());
        model.addAttribute("totalAtivos", ativos);
        model.addAttribute("totalBloqueados", bloqueados);

        return "usuarios";
    }

    @PostMapping("/usuarios")
    public String salvar(@ModelAttribute("usuario") Usuario usuario) {
        if (usuario.getId() != null && usuario.getId().trim().isEmpty()) {
            usuario.setId(null);
        }
        service.salvar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/excluir/{id}")
    public String excluir(@PathVariable String id) {
        service.excluir(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/alternar/{id}")
    public String alternar(@PathVariable String id) {
        service.alternarStatus(id);
        return "redirect:/usuarios";
    }
}