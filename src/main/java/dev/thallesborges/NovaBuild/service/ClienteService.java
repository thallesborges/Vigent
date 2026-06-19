package dev.thallesborges.NovaBuild.service;

import dev.thallesborges.NovaBuild.database.entity.ClienteEntity;
import dev.thallesborges.NovaBuild.database.entity.PlanoEntity;
import dev.thallesborges.NovaBuild.database.repository.ClienteRepository;
import dev.thallesborges.NovaBuild.database.repository.PlanoRepository;
import dev.thallesborges.NovaBuild.dto.request.CadastrarClienteRequest;
import dev.thallesborges.NovaBuild.dto.response.ClienteResponse;
import dev.thallesborges.NovaBuild.exception.*;
import dev.thallesborges.NovaBuild.validators.CnpjValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final PlanoRepository planoRepository;

    public ClienteResponse cadastrarCliente(CadastrarClienteRequest request) {
        if (clienteRepository.existsByEmailPrincipalOrEmailFinanceiro(request.emailPrincipal(), request.emailFinanceiro())) {
            throw new EmailExisteException("E-mail principal ou financeiro já cadastrados no sistema");
        } else if (clienteRepository.existsByCnpj(request.cnpj())) {
            throw new CnpjInvalidoException("CPNJ já cadastrado no sistema");
        } else if (clienteRepository.existsByRazaoSocial(request.razaoSocial())) {
            throw new RazaoSocialExisteException("Razão Social já cadastrada no sistema");
        } else if (clienteRepository.existsByTelefone(request.telefone())) {
            throw new TelefoneExisteException("Telefone já cadastrado no sistema");
        }

        if (request.cnpj().length() != 14) {
            throw new CnpjInvalidoException("CNPJ deve conter exatamente 14 dígitos numéricos");
        } else if (!CnpjValidator.isValido(request.cnpj())) {
            throw new CnpjInvalidoException("CNPJ inválido");
        }

        PlanoEntity plano = planoRepository.findByNome(request.plano())
                .orElseThrow(() -> new PlanoNotFoundException("Plano não encontrado: " + request.plano()));

        ClienteEntity novoCliente = ClienteEntity.builder()
                .razaoSocial(request.razaoSocial())
                .nomeFantasia(request.nomeFantasia())
                .cnpj(request.cnpj())
                .emailPrincipal(request.emailPrincipal())
                .dataContratacao(LocalDate.now())
                .telefone(request.telefone())
                .emailFinanceiro(request.emailFinanceiro())
                .plano(plano)
                .build();

        ClienteEntity savedCliente = clienteRepository.save(novoCliente);

        return new ClienteResponse(
                savedCliente.getId(),
                savedCliente.getRazaoSocial(),
                savedCliente.getNomeFantasia(),
                savedCliente.getCnpj(),
                savedCliente.getEmailPrincipal(),
                savedCliente.getDataContratacao(),
                savedCliente.getTelefone()
        );
    }
}
