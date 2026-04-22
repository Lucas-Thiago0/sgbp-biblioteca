package br.com.umc.sgbp_biblioteca.repository;

import br.com.umc.sgbp_biblioteca.model.Livro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {
}