package br.com.umc.sgbp_biblioteca.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "livros")
public class Livro {
    @Id
    private String id;
    private String titulo;
    private String autor;
    private String editora;
    private String categoria;
    private String isbn;
    private int anoPublicacao;
    private int exemplares;
    private String localizacao;
    private boolean disponivel = true;
}