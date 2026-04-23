package br.com.umc.sgbp_biblioteca.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    private String nome;
    private String email;
    private String matricula;

    /** ALUNO | PROFESSOR | FUNCIONARIO */
    private String perfil = "ALUNO";

    private String curso;

    /** true = ativo, false = bloqueado */
    private boolean ativo = true;
}
