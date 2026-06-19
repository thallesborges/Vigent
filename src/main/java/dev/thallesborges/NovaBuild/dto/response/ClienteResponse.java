package dev.thallesborges.NovaBuild.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteResponse(
        @NotNull
        Long id,

        @NotBlank
        String razaoSocial,

        @NotBlank
        String nomeFantasia,

        @NotBlank
        String cnpj,

        @NotBlank
        String emailPrincipal,

        @NotBlank
        LocalDate dataContratacao,

        @NotBlank
        String telefone
) {}
