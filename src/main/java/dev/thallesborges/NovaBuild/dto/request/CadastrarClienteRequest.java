package dev.thallesborges.NovaBuild.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastrarClienteRequest(
        @NotBlank
        String razaoSocial,

        @NotBlank
        String nomeFantasia,

        @NotBlank
        String cnpj,

        @NotBlank
        @Email
        String emailPrincipal,

        @Email
        String emailFinanceiro,

        @NotBlank
        String telefone,

        @NotBlank
        String plano
) {}
