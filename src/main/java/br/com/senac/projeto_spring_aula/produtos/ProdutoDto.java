package br.com.senac.projeto_spring_aula.produtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProdutoDto(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "A quantidade é obrigatória")
        @PositiveOrZero(message = "A quantidade deve ser maior ou igual a zero")
        Integer quantidadeEstoque

) {
}