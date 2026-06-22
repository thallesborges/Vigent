package dev.thallesborges.NovaBuild.cliente;

import java.time.LocalDate;

public record ClienteResponse(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String emailPrincipal,
        String emailFinanceiro,
        LocalDate dataContratacao,
        String telefone
) {}
