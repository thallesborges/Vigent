package dev.thallesborges.NovaBuild.plano;

import dev.thallesborges.NovaBuild.exception.ResourceExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                savedPlano.getClientePlanoStatus()
        );
    }

    public List<PlanoResponse> listarPlanos() {
        return planoRepository.findAll().stream()
                .map(p -> new PlanoResponse(
                        p.getId(), p.getNome(), p.getPreco(), p.getClientePlanoStatus()
                ))
                .toList();
    }
}
