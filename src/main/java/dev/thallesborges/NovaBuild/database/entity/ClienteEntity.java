package dev.thallesborges.NovaBuild.database.entity;

import dev.thallesborges.NovaBuild.enums.Status;
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
public class ClienteEntity {
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
    private Status status =  Status.ATIVO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id")
    private PlanoEntity plano;

    @OneToOne(mappedBy = "cliente")
    private AssinaturaEntity assinatura;
}
