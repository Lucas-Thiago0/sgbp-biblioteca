package br.com.umc.sgbp_biblioteca.controller;

import br.com.umc.sgbp_biblioteca.model.Livro;
import br.com.umc.sgbp_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Import essencial para não dar erro de "Symbol not found"

@Controller
public class LivroController {

    @Autowired
    private LivroService service;

    // Redireciona a raiz para o Dashboard
    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    // TELA: Painel Geral (Cálculos para os Cards do Figma)
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Livro> todos = service.listarTodos();

        // Estatísticas para o Dashboard Teal
        long totalLivros       = todos.size();
        long livrosDisponiveis = todos.stream().filter(Livro::isDisponivel).count();
        long livrosEmprestados = todos.stream().filter(l -> !l.isDisponivel()).count();
        long totalExemplares   = todos.stream().mapToLong(Livro::getExemplares).sum();

        model.addAttribute("totalLivros",       totalLivros);
        model.addAttribute("livrosDisponiveis", livrosDisponiveis);
        model.addAttribute("livrosEmprestados", livrosEmprestados);
        model.addAttribute("totalExemplares",   totalExemplares);

        // Pega os últimos 5 cadastrados para a tabela de recentes
        model.addAttribute("livrosRecentes",
                todos.size() > 5 ? todos.subList(todos.size() - 5, todos.size()) : todos);

        return "dashboard";
    }

    // TELA: Gestão de Acervo (Lista completa)
    @GetMapping("/livros")
    public String listar(Model model) {
        model.addAttribute("livros", service.listarTodos());
        return "lista-livros";
    }

    // TELA: Formulário de Cadastro
    @GetMapping("/livros/novo")
    public String novoForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "cadastro-livro";
    }

    // AÇÃO: Salvar Livro (Com correção de ID vazio)
    @PostMapping("/livros")
    public String salvar(@ModelAttribute("livro") Livro livro) {
        // Resolve o bug de sobrescrever o ID anterior no MongoDB
        if (livro.getId() != null && livro.getId().trim().isEmpty()) {
            livro.setId(null);
        }
        service.salvar(livro);
        return "redirect:/livros";
    }

    // TELA: Formulário de Edição
    @GetMapping("/livros/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        service.buscarPorId(id).ifPresent(livro -> model.addAttribute("livro", livro));
        return "cadastro-livro";
    }

    // AÇÃO: Excluir Livro
    @GetMapping("/livros/excluir/{id}")
    public String excluir(@PathVariable String id) {
        service.excluir(id);
        return "redirect:/livros";
    }

    // AÇÃO: Alternar Status (Disponível / Emprestado)
    @GetMapping("/livros/alternar/{id}")
    public String alternarStatus(@PathVariable String id) {
        service.alternarStatus(id);
        return "redirect:/livros";
    }
}