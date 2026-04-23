package br.com.umc.sgbp_biblioteca.controller;

import br.com.umc.sgbp_biblioteca.model.Livro;
import br.com.umc.sgbp_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LivroController {

    @Autowired
    private LivroService service;

    // Rota Raiz
    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    // TELA: Painel Geral (Página 1 do PDF)
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalLivros", service.listarTodos().size());
        return "dashboard";
    }

    // TELA: Gestão de Acervo (Listagem com todas as colunas)
    @GetMapping("/livros")
    public String listar(Model model) {
        model.addAttribute("livros", service.listarTodos());
        return "lista-livros";
    }

    // ROTAS DE NAVEGAÇÃO (Páginas 2 e 3 do PDF)
    @GetMapping("/usuarios")
    public String usuarios() { return "usuarios"; }

    @GetMapping("/emprestimos")
    public String emprestimos() { return "emprestimos"; }

    @GetMapping("/reservas")
    public String reservas() { return "reservas"; }

    @GetMapping("/atendimento")
    public String atendimento() { return "atendimento"; }

    // FORMULÁRIO: Novo Livro (Título, Autor, Editora, Categoria, ISBN, Ano)
    @GetMapping("/livros/novo")
    public String novoForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "cadastro-livro";
    }

    // AÇÃO: Salvar (Garante que Editora e Categoria sejam persistidos)
    @PostMapping("/livros")
    public String salvar(@ModelAttribute("livro") Livro livro) {
        // Correção para não sobrescrever registros ao criar um novo
        if (livro.getId() != null && livro.getId().trim().isEmpty()) {
            livro.setId(null);
        }

        service.salvar(livro); // O service envia o objeto completo ao MongoDB Atlas
        return "redirect:/livros";
    }

    @GetMapping("/livros/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        service.buscarPorId(id).ifPresent(livro -> model.addAttribute("livro", livro));
        return "cadastro-livro";
    }

    @GetMapping("/livros/excluir/{id}")
    public String excluir(@PathVariable String id) {
        service.excluir(id);
        return "redirect:/livros";
    }

    @GetMapping("/livros/alternar/{id}")
    public String alternarStatus(@PathVariable String id) {
        service.alternarStatus(id);
        return "redirect:/livros";
    }
}