package br.com.umc.sgbp_biblioteca.repository;

import br.com.umc.sgbp_biblioteca.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    // Spring Data gera automaticamente: findAll, save, deleteById, findById
}