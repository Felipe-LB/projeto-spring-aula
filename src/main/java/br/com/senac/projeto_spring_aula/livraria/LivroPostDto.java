package br.com.senac.projeto_spring_aula.livraria;


import jakarta.validation.constraints.NotBlank;

public record LivroPostDto(
        @NotBlank(message = "O campo título é obrigatório!")
        String titulo,
        @NotBlank(message = "O campo autor é obrigatório!")
        String autor,
        Integer anoPublicacao
) {

}
