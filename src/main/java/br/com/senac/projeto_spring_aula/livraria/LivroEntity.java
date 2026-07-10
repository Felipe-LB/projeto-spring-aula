package br.com.senac.projeto_spring_aula.livraria;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "livro")
@Getter
@Setter
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    private Boolean disponivel;

    @PrePersist
    public void prePersist() {
        if (disponivel == null) {
            disponivel = true;
        }
    }
}
