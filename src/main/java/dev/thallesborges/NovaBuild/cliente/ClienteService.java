package dev.thallesborges.NovaBuild.cliente;

import dev.thallesborges.NovaBuild.exception.*;
import dev.thallesborges.NovaBuild.cliente.validators.CnpjValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteResponse cadastrarCliente(CadastrarClienteRequest request) {
        if (clienteRepository.existsByEmailPrincipal(request.emailPrincipal())) {
            throw new ResourceExistenteException("E-mail principal ocadastrado no sistema: " + request.emailPrincipal());
        } else if (clienteRepository.existsByEmailFinanceiro(request.emailFinanceiro())) {
            throw new ResourceExistenteException("E-mail financeiro já cadastrado no sistema: " + request.emailFinanceiro());
        } else if (clienteRepository.existsByCnpj(request.cnpj())) {
            throw new ResourceExistenteException("CPNJ já cadastrado no sistema: " + request.cnpj());
        } else if (clienteRepository.existsByRazaoSocial(request.razaoSocial())) {
            throw new ResourceExistenteException("Razão Social já cadastrada no sistema: " + request.razaoSocial());
        } else if (clienteRepository.existsByTelefone(request.telefone())) {
            throw new ResourceExistenteException("Telefone já cadastrado no sistema: " + request.telefone());
        }

        String cnpj = request.cnpj().replaceAll("\\D", "");
        Long numeroCnpj =  Long.parseLong(cnpj);
        if (cnpj.length() != 14) {
            throw new CnpjInvalidoException("CNPJ deve conter exatamente 14 dígitos numéricos");
        } else if (!CnpjValidator.isValido(numeroCnpj)) {
            throw new CnpjInvalidoException("CNPJ inválido");
        }

        ClienteEntity novoCliente = ClienteEntity.builder()
                .razaoSocial(request.razaoSocial())
                .nomeFantasia(request.nomeFantasia())
                .cnpj(cnpj)
                .emailPrincipal(request.emailPrincipal())
                .dataContratacao(request.dataContratacao())
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
                savedCliente.getEmailFinanceiro(),
                savedCliente.getDataContratacao(),
                savedCliente.getTelefone()
        );
    }

    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(c -> new ClienteResponse(
                        c.getId(), c.getRazaoSocial(), c.getNomeFantasia(),
                        c.getCnpj(), c.getEmailPrincipal(), c.getEmailFinanceiro(),
                        c.getDataContratacao(), c.getTelefone()))
                .toList();
    }

    public ClienteResponse updateCliente(Long id, CadastrarClienteRequest request) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID de Cliente não encontrado: " + id));

        if (request.razaoSocial() != null) cliente.setRazaoSocial(request.razaoSocial());
        if (request.nomeFantasia() != null) cliente.setNomeFantasia(request.nomeFantasia());
        if (request.cnpj() != null) cliente.setCnpj(request.cnpj());
        if (request.emailPrincipal() != null) cliente.setEmailPrincipal(request.emailPrincipal());
        if (request.dataContratacao() != null) cliente.setDataContratacao(request.dataContratacao());
        if (request.telefone() != null) cliente.setTelefone(request.telefone());
        if (request.emailFinanceiro() != null) cliente.setEmailFinanceiro(request.emailFinanceiro());

        clienteRepository.save(cliente);

        return new ClienteResponse(
                cliente.getId(),
                cliente.getRazaoSocial(),
                cliente.getNomeFantasia(),
                cliente.getCnpj(),
                cliente.getEmailPrincipal(),
                cliente.getEmailFinanceiro(),
                cliente.getDataContratacao(),
                cliente.getTelefone()
        );
    }
}
