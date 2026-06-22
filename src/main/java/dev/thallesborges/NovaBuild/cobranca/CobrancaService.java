package dev.thallesborges.NovaBuild.cobranca;

import dev.thallesborges.NovaBuild.assinatura.AssinaturaEntity;
import dev.thallesborges.NovaBuild.assinatura.AssinaturaRepository;
import dev.thallesborges.NovaBuild.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CobrancaService {
    private final CobrancaRepository cobrancaRepository;
    private final AssinaturaRepository assinaturaRepository;

    public CobrancaResponse cadastrarCobranca(CadastrarCobrancaRequest request) {
        AssinaturaEntity assinatura = assinaturaRepository.findById(request.assinaturaId())
                .orElseThrow(() -> new ResourceNotFoundException("ID de Assinatura não encontrado: " + request.assinaturaId()));

        CobrancaEntity novaCobranca = CobrancaEntity.builder()
                .assinatura(assinatura)
                .valor(assinatura.getPlano().getPreco())
                .vencimento(request.vencimento())
                .build();

        CobrancaEntity savedCobranca = cobrancaRepository.save(novaCobranca);

        return new CobrancaResponse(
                savedCobranca.getId(),
                savedCobranca.getAssinatura().getCliente().getRazaoSocial(),
                savedCobranca.getAssinatura().getPlano().getNome(),
                savedCobranca.getAssinatura().getPlano().getPreco(),
                savedCobranca.getVencimento()
        );
    }

}
