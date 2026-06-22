package dev.thallesborges.NovaBuild.cobranca;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastrarCobrancaRequest(
        @NotNull
        Long assinaturaId,

        @NotBlank
        @FutureOrPresent
        LocalDate vencimento
) {}
