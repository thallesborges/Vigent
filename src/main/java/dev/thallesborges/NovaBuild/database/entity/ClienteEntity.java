package dev.thallesborges.NovaBuild.database.entity;

import dev.thallesborges.NovaBuild.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String razaoSocial;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nomeFantasia;

    @NotBlank
    @Size(max = 14)
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String emailPrincipal;

    @NotBlank
    @Column(nullable = false)
    private LocalDate dataContratacao;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String telefone;

    @Email
    @Size(max = 100)
    @Column(unique = true, length = 100)
    private String emailFinanceiro;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private Status status =  Status.ATIVO;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id", nullable = false)
    private PlanoEntity plano;
}
