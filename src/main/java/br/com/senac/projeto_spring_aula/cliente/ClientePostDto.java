package br.com.senac.projeto_spring_aula.cliente;

import jakarta.validation.constraints.*;


public record ClientePostDto(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "O CPF é obrigatório")
        @Size(min = 11, max = 11, message = "O cpf deve ter 11 caracteres!")
        String cpf

) {
}
