package br.com.umc.sgbp_biblioteca.controller;

import br.com.umc.sgbp_biblioteca.model.Livro;
import br.com.umc.sgbp_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LivroController {

    @Autowired
    private LivroService service;

    // ──────────────────────────────────────────
    // ROTA RAIZ
    // ──────────────────────────────────────────
    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    // ──────────────────────────────────────────
    // DASHBOARD — Painel Geral
    // ──────────────────────────────────────────
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Livro> todos = service.listarTodos();

        long totalLivros      = todos.size();
        long livrosDisponiveis = todos.stream().filter(Livro::isDisponivel).count();
        long livrosEmprestados = todos.stream().filter(l -> !l.isDisponivel()).count();
        long totalExemplares  = todos.stream().mapToLong(Livro::getExemplares).sum();

        model.addAttribute("totalLivros",       totalLivros);
        model.addAttribute("livrosDisponiveis", livrosDisponiveis);
        model.addAttribute("livrosEmprestados", livrosEmprestados);
        model.addAttribute("totalExemplares",   totalExemplares);
        // Últimos 5 livros adicionados para o card de "Atividade Recente"
        model.addAttribute("livrosRecentes",
                todos.size() > 5 ? todos.subList(todos.size() - 5, todos.size()) : todos);

        return "dashboard";
    }

    // ──────────────────────────────────────────
    // GESTÃO DE ACERVO — Listagem
    // ──────────────────────────────────────────
    @GetMapping("/livros")
    public String listar(Model model) {
        model.addAttribute("livros", service.listarTodos());
        return "lista-livros";
    }

    // ──────────────────────────────────────────
    // FORMULÁRIO: Novo Livro
    // ──────────────────────────────────────────
    @GetMapping("/livros/novo")
    public String novoForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "cadastro-livro";
    }

    // ──────────────────────────────────────────
    // AÇÃO: Salvar / Atualizar (upsert correto)
    // ──────────────────────────────────────────
    @PostMapping("/livros")
    public String salvar(@ModelAttribute("livro") Livro livro) {
        // Garante que um ID vazio não sobrescreva registros existentes
        if (livro.getId() != null && livro.getId().trim().isEmpty()) {
            livro.setId(null);
        }
        service.salvar(livro);
        return "redirect:/livros";
    }

    // ──────────────────────────────────────────
    // FORMULÁRIO: Editar Livro
    // ──────────────────────────────────────────
    @GetMapping("/livros/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        service.buscarPorId(id).ifPresent(livro -> model.addAttribute("livro", livro));
        return "cadastro-livro";
    }

    // ──────────────────────────────────────────
    // AÇÃO: Excluir Livro
    // ──────────────────────────────────────────
    @GetMapping("/livros/excluir/{id}")
    public String excluir(@PathVariable String id) {
        service.excluir(id);
        return "redirect:/livros";
    }

    // ──────────────────────────────────────────
    // AÇÃO: Alternar Status Disponível/Emprestado
    // ──────────────────────────────────────────
    @GetMapping("/livros/alternar/{id}")
    public String alternarStatus(@PathVariable String id) {
        service.alternarStatus(id);
        return "redirect:/livros";
    }

    // ──────────────────────────────────────────
    // TELAS SECUNDÁRIAS (navegação da sidebar)
    // ──────────────────────────────────────────
    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios";
    }

    @GetMapping("/emprestimos")
    public String emprestimos() {
        return "emprestimos";
    }

    @GetMapping("/reservas")
    public String reservas(Model model) {
        // Quando o model Reserva for implementado, injete ReservaService aqui.
        // Por ora, passa listas vazias para a view não quebrar.
        model.addAttribute("reservas",         java.util.Collections.emptyList());
        model.addAttribute("totalReservas",    0);
        model.addAttribute("reservasAtivas",   0);
        model.addAttribute("reservasExpiradas", 0);
        return "reservas";
    }

    @GetMapping("/atendimento")
    public String atendimento(Model model) {
        // Quando o model Atendimento for implementado, injete AtendimentoService aqui.
        model.addAttribute("atendimentos", java.util.Collections.emptyList());
        return "atendimento";
    }

    // ──────────────────────────────────────────
    // AÇÕES DE RESERVA (stub — prontas para implementar)
    // ──────────────────────────────────────────
    @PostMapping("/reservas")
    public String salvarReserva(@RequestParam(required = false) String nomeUsuario,
                                @RequestParam(required = false) String emailUsuario,
                                @RequestParam(required = false) String tituloLivro,
                                @RequestParam(required = false) String dataValidade) {
        // TODO: implementar ReservaService e persistir no MongoDB
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/confirmar/{id}")
    public String confirmarReserva(@PathVariable String id) {
        // TODO: atualizar status para CONFIRMADA
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/cancelar/{id}")
    public String cancelarReserva(@PathVariable String id) {
        // TODO: atualizar status para CANCELADA
        return "redirect:/reservas";
    }

    // ──────────────────────────────────────────
    // AÇÕES DE ATENDIMENTO (stub — prontas para implementar)
    // ──────────────────────────────────────────
    @PostMapping("/atendimento/emprestar")
    public String registrarEmprestimo(@RequestParam(required = false) String nomeUsuario,
                                      @RequestParam(required = false) String emailUsuario,
                                      @RequestParam(required = false) String tituloLivro,
                                      @RequestParam(required = false) String dataDevolucaoPrevista) {
        // TODO: implementar AtendimentoService, marcar livro como indisponível
        return "redirect:/atendimento";
    }

    @PostMapping("/atendimento/devolver")
    public String registrarDevolucao(@RequestParam(required = false) String nomeUsuario,
                                     @RequestParam(required = false) String tituloLivro,
                                     @RequestParam(required = false) String estadoLivro,
                                     @RequestParam(required = false) String observacao) {
        // TODO: implementar AtendimentoService, marcar livro como disponível
        return "redirect:/atendimento";
    }

    @PostMapping("/atendimento/ocorrencia")
    public String registrarOcorrencia(@RequestParam(required = false) String nomeUsuario,
                                      @RequestParam(required = false) String tituloLivro,
                                      @RequestParam(required = false) String tipoOcorrencia,
                                      @RequestParam(required = false) String descricao) {
        // TODO: implementar AtendimentoService
        return "redirect:/atendimento";
    }
}