package dev.thallesborges.NovaBuild.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    boolean existsByEmailPrincipal(String emailPrincipal);
    boolean existsByEmailFinanceiro(String emailFinanceiro);
    boolean existsByCnpj(String cnpj);
    boolean existsByRazaoSocial(String razaoSocial);
    boolean existsByTelefone(String telefone);

    Optional<ClienteEntity> findByRazaoSocial(String razaoSocial);
}
