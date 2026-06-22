package dev.thallesborges.NovaBuild.assinatura;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
@RequiredArgsConstructor
public class AssinaturaController {
    private final AssinaturaService assinaturaService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public AssinaturaResponse cadastrarAssinatura(@Valid @RequestBody CadastrarAssinaturaRequest request) {
        return assinaturaService.cadastrarAssinatura(request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AssinaturaResponse> listarAssinaturas() {
        return assinaturaService.listarAssinaturas();
    }
}
