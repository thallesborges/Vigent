package dev.thallesborges.NovaBuild.database.repository;

import dev.thallesborges.NovaBuild.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    boolean existsByEmailPrincipalOrEmailFinanceiro(String emailPrincipal, String emailFinanceiro);
    boolean existsByCnpj(String cnpj);
    boolean existsByRazaoSocial(String razaoSocial);
    boolean existsByTelefone(String telefone);

    Optional<ClienteEntity> findByRazaoSocial(String razaoSocial);
}
