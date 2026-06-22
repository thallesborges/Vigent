package dev.thallesborges.NovaBuild.assinatura;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastrarAssinaturaRequest(
        @NotBlank
        String razaoSocialCliente,

        @NotBlank
        String nomePlano,

        @NotNull
        LocalDate dataInicio
) {}
