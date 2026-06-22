package dev.thallesborges.NovaBuild.assinatura;

import dev.thallesborges.NovaBuild.cliente.ClienteEntity;
import dev.thallesborges.NovaBuild.cobranca.CobrancaEntity;
import dev.thallesborges.NovaBuild.database.entity.AuditoriaEntity;
import dev.thallesborges.NovaBuild.plano.PlanoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "assinaturas")
public class AssinaturaEntity extends AuditoriaEntity {
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

    @OneToMany(mappedBy = "assinatura")
    private List<CobrancaEntity> cobrancas;
}
