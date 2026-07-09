package br.com.senac.projeto_spring_aula.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "lista")
@Getter
@Setter
public class ListaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Enumerated(EnumType.STRING)
    private ListaStatus status;
}


