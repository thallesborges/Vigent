package dev.thallesborges.NovaBuild.dto.response;

import dev.thallesborges.NovaBuild.enums.Status;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

public record PlanoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        Status status
) {}
