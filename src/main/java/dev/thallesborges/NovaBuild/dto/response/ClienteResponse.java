package dev.thallesborges.NovaBuild.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteResponse(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String emailPrincipal,
        LocalDate dataContratacao,
        String telefone
) {}
