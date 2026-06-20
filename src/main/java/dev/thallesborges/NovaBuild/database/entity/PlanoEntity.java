package dev.thallesborges.NovaBuild.database.entity;

import dev.thallesborges.NovaBuild.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "planos")
public class PlanoEntity extends AuditoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ATIVO;

    @OneToMany(mappedBy = "plano", fetch = FetchType.LAZY)
    private List<AssinaturaEntity> assinaturas = new ArrayList<>();
}
