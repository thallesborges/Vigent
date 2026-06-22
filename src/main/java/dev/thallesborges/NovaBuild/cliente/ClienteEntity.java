package dev.thallesborges.NovaBuild.cliente;

import dev.thallesborges.NovaBuild.assinatura.AssinaturaEntity;
import dev.thallesborges.NovaBuild.database.entity.AuditoriaEntity;
import dev.thallesborges.NovaBuild.enums.ClientePlanoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "clientes")
public class ClienteEntity extends AuditoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String emailPrincipal;
    private LocalDate dataContratacao;
    private String telefone;
    private String emailFinanceiro;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ClientePlanoStatus clientePlanoStatus =  ClientePlanoStatus.ATIVO;

    @OneToOne(mappedBy = "cliente")
    private AssinaturaEntity assinatura;
}
