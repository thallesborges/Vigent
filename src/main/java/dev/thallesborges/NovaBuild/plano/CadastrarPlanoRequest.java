package dev.thallesborges.NovaBuild.plano;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastrarPlanoRequest (
        @NotBlank
        String nome,

        @NotNull
        BigDecimal preco
) {}