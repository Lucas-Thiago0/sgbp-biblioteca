package br.com.umc.sgbp_biblioteca.service;

import br.com.umc.sgbp_biblioteca.model.Usuario;
import br.com.umc.sgbp_biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return repository.findById(id);
    }

    public void salvar(Usuario usuario) {
        repository.save(usuario);
    }

    public void excluir(String id) {
        repository.deleteById(id);
    }

    public void alternarStatus(String id) {
        repository.findById(id).ifPresent(u -> {
            u.setAtivo(!u.isAtivo());
            repository.save(u);
        });
    }
}
