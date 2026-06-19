package dev.thallesborges.NovaBuild.database.entity;

import dev.thallesborges.NovaBuild.enums.AssinaturaStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "assinaturas")
public class AssinaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id")
    private PlanoEntity plano;

    private LocalDate dataInicio;
    private LocalDate dataTermino;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AssinaturaStatus status = AssinaturaStatus.ATIVA;
}
