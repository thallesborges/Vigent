package dev.thallesborges.NovaBuild.plano;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {
    private final PlanoService planoService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public PlanoResponse cadastrarPlano(@Valid @RequestBody CadastrarPlanoRequest request) {
        return planoService.cadastrarPlano(request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PlanoResponse> listarPlanos() {
        return planoService.listarPlanos();
    }
}
