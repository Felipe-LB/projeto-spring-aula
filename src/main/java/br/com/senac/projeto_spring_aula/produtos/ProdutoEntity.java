package br.com.senac.projeto_spring_aula.produtos;


import br.com.senac.projeto_spring_aula.todolist.model.ListaStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private int quantidadeEstoque;

    @Enumerated(EnumType.STRING)
    private ProdutoStatus status;
}

