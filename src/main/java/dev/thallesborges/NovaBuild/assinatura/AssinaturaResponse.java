package dev.thallesborges.NovaBuild.assinatura;

import java.time.LocalDate;

public record AssinaturaResponse(
        Long id,

        String razaoSocialCliente,
        Long clienteId,

        String nomePlano,
        Long idPlano,

        LocalDate dataInicio,
        LocalDate dataTermino,

        AssinaturaStatus status
) {}
