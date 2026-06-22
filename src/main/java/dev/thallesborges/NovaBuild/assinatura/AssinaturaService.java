package dev.thallesborges.NovaBuild.assinatura;

import dev.thallesborges.NovaBuild.cliente.ClienteEntity;
import dev.thallesborges.NovaBuild.plano.PlanoEntity;
import dev.thallesborges.NovaBuild.cliente.ClienteRepository;
import dev.thallesborges.NovaBuild.plano.PlanoRepository;
import dev.thallesborges.NovaBuild.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AssinaturaResponse> listarAssinaturas() {
        return assinaturaRepository.findAll().stream()
                .map(a -> new AssinaturaResponse(
                        a.getId(),
                        a.getCliente().getRazaoSocial(), a.getCliente().getId(),
                        a.getPlano().getNome(), a.getPlano().getId(),
                        a.getDataInicio(), a.getDataTermino(),
                        a.getStatus()))
                .toList();
    }
}
