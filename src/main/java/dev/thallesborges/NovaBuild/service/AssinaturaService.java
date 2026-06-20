package dev.thallesborges.NovaBuild.service;

import dev.thallesborges.NovaBuild.database.entity.AssinaturaEntity;
import dev.thallesborges.NovaBuild.database.entity.ClienteEntity;
import dev.thallesborges.NovaBuild.database.entity.PlanoEntity;
import dev.thallesborges.NovaBuild.database.repository.AssinaturaRepository;
import dev.thallesborges.NovaBuild.database.repository.ClienteRepository;
import dev.thallesborges.NovaBuild.database.repository.PlanoRepository;
import dev.thallesborges.NovaBuild.dto.request.CadastrarAssinaturaRequest;
import dev.thallesborges.NovaBuild.dto.response.AssinaturaResponse;
import dev.thallesborges.NovaBuild.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssinaturaService {
    private final AssinaturaRepository assinaturaRepository;
    private final ClienteRepository clienteRepository;
    private final PlanoRepository planoRepository;

    public AssinaturaResponse cadastrarAssinatura(CadastrarAssinaturaRequest request) {
        ClienteEntity cliente = clienteRepository.findByRazaoSocial(request.razaoSocialCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + request.razaoSocialCliente()));

        PlanoEntity plano = planoRepository.findByNome(request.nomePlano())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado: " + request.nomePlano()));

        AssinaturaEntity novaAssinatura = AssinaturaEntity.builder()
                .cliente(cliente)
                .plano(plano)
                .dataInicio(request.dataInicio())
                .dataTermino(null)
                .build();

        AssinaturaEntity savedAssinatura =  assinaturaRepository.save(novaAssinatura);

        return new AssinaturaResponse(
                savedAssinatura.getId(),
                cliente.getRazaoSocial(),
                cliente.getId(),
                plano.getNome(),
                plano.getId(),
                savedAssinatura.getDataInicio(),
                savedAssinatura.getDataTermino(),
                savedAssinatura.getStatus()
        );
    }
}
