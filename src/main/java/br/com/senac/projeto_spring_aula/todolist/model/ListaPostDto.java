package br.com.senac.projeto_spring_aula.todolist.model;

import jakarta.validation.constraints.NotBlank;

public record ListaPostDto(
       @NotBlank(message = "O campo descrição é obrigatório!")
        String descricao
) {

}
