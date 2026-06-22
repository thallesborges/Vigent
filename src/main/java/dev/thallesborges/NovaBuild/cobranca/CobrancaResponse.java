package dev.thallesborges.NovaBuild.cobranca;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CobrancaResponse(
        @NotNull
        Long id,

        @NotBlank
        String razaoSocialCliente,

        @NotBlank
        String plano,

        @NotNull
        @Positive
        BigDecimal valor,

        @NotBlank
        @FutureOrPresent
        LocalDate vencimento
) {}
