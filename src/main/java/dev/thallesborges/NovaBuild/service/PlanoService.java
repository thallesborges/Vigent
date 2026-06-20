package dev.thallesborges.NovaBuild.service;

import dev.thallesborges.NovaBuild.database.entity.PlanoEntity;
import dev.thallesborges.NovaBuild.database.repository.PlanoRepository;
import dev.thallesborges.NovaBuild.dto.request.CadastrarPlanoRequest;
import dev.thallesborges.NovaBuild.dto.response.PlanoResponse;
import dev.thallesborges.NovaBuild.exception.ResourceExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanoService {
    private final PlanoRepository planoRepository;

    public PlanoResponse cadastrarPlano(CadastrarPlanoRequest request){
        if (planoRepository.existsByNome(request.nome())) {
            throw new ResourceExistenteException("Nome já cadastrado previamente: " + request.nome());
        }

        PlanoEntity novoPlano = PlanoEntity.builder()
                .nome(request.nome())
                .preco(request.preco())
                .build();

        PlanoEntity savedPlano = planoRepository.save(novoPlano);

        return new PlanoResponse(
                savedPlano.getId(),
                savedPlano.getNome(),
                savedPlano.getPreco(),
                savedPlano.getStatus()
        );
    }
}
