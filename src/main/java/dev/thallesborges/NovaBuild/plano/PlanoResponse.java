package dev.thallesborges.NovaBuild.plano;

import dev.thallesborges.NovaBuild.enums.ClientePlanoStatus;

import java.math.BigDecimal;

public record PlanoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        ClientePlanoStatus clientePlanoStatus
) {}
