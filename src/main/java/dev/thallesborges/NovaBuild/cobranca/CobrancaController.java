package dev.thallesborges.NovaBuild.cobranca;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cobranca")
@RequiredArgsConstructor
public class CobrancaController {
    private final CobrancaService cobrancaService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public CobrancaResponse cadastrarCobranca(@Valid @RequestBody CadastrarCobrancaRequest request) {
        return cobrancaService.cadastrarCobranca(request);
    }
}
