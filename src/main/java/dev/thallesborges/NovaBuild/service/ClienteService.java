package dev.thallesborges.NovaBuild.service;

import dev.thallesborges.NovaBuild.database.entity.ClienteEntity;
import dev.thallesborges.NovaBuild.database.repository.ClienteRepository;
import dev.thallesborges.NovaBuild.database.repository.PlanoRepository;
import dev.thallesborges.NovaBuild.dto.request.CadastrarClienteRequest;
import dev.thallesborges.NovaBuild.dto.response.ClienteResponse;
import dev.thallesborges.NovaBuild.exception.*;
import dev.thallesborges.NovaBuild.validators.CnpjValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final PlanoRepository planoRepository;

    public ClienteResponse cadastrarCliente(CadastrarClienteRequest request) {
        if (clienteRepository.existsByEmailPrincipal(request.emailPrincipal())) {
            throw new ResourceExistenteException("E-mail principal ocadastrado no sistema: " + request.emailPrincipal());
        } else if (clienteRepository.existsByCnpj(request.cnpj())) {
            throw new ResourceExistenteException("CPNJ já cadastrado no sistema: " + request.cnpj());
        } else if (clienteRepository.existsByRazaoSocial(request.razaoSocial())) {
            throw new ResourceExistenteException("Razão Social já cadastrada no sistema: " + request.razaoSocial());
        } else if (clienteRepository.existsByTelefone(request.telefone())) {
            throw new ResourceExistenteException("Telefone já cadastrado no sistema: " + request.telefone());
        }

        String regexCnpj = request.cnpj().replaceAll("\\D", "");
        Long cnpj = Long.parseLong(regexCnpj);

        if (regexCnpj.length() != 14) {
            throw new CnpjInvalidoException("CNPJ deve conter exatamente 14 dígitos numéricos");
        } else if (!CnpjValidator.isValido(cnpj)) {
            throw new CnpjInvalidoException("CNPJ inválido");
        }

        ClienteEntity novoCliente = ClienteEntity.builder()
                .razaoSocial(request.razaoSocial())
                .nomeFantasia(request.nomeFantasia())
                .cnpj(request.cnpj())
                .emailPrincipal(request.emailPrincipal())
                .dataContratacao(LocalDate.now())
                .telefone(request.telefone())
                .emailFinanceiro(request.emailFinanceiro())
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
