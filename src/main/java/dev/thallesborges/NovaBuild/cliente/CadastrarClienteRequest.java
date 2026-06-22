package dev.thallesborges.NovaBuild.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

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

        @NotNull
        @PastOrPresent
        LocalDate dataContratacao
) {}
