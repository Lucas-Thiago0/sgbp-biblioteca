package br.com.umc.sgbp_biblioteca.service;

import br.com.umc.sgbp_biblioteca.model.Livro;
import br.com.umc.sgbp_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public void salvar(Livro livro) {
        repository.save(livro);
    }

    public Optional<Livro> buscarPorId(String id) {
        return repository.findById(id);
    }

    public void excluir(String id) {
        repository.deleteById(id);
    }
}