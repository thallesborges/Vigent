package dev.thallesborges.NovaBuild.database.entity;

import dev.thallesborges.NovaBuild.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "planos")
public class PlanoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private Status status = Status.ATIVO;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Instant criadoEm;

    @OneToMany(mappedBy = "plano", fetch = FetchType.LAZY)
    private List<ClienteEntity> clientes = new ArrayList<>();
}
