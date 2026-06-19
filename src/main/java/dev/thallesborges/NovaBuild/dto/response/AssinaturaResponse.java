package dev.thallesborges.NovaBuild.dto.response;

import dev.thallesborges.NovaBuild.enums.AssinaturaStatus;

import java.time.LocalDate;

public record AssinaturaResponse(
        Long id,

        String razaoSocialCliente,
        Long cliente_id,

        String nomePlano,
        Long id_plano,

        LocalDate dataInicio,
        LocalDate dataTermino,

        AssinaturaStatus status
) {}
