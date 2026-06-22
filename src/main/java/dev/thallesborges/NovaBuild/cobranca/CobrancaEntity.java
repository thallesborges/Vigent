package dev.thallesborges.NovaBuild.cobranca;

import dev.thallesborges.NovaBuild.assinatura.AssinaturaEntity;
import dev.thallesborges.NovaBuild.database.entity.AuditoriaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "cobrancas")
public class CobrancaEntity extends AuditoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private LocalDate vencimento;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CobrancaStatus status = CobrancaStatus.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "assinatura_id")
    private AssinaturaEntity assinatura;
}
