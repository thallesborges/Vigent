package dev.thallesborges.NovaBuild.controller;

import dev.thallesborges.NovaBuild.dto.request.CadastrarAssinaturaRequest;
import dev.thallesborges.NovaBuild.dto.response.AssinaturaResponse;
import dev.thallesborges.NovaBuild.service.AssinaturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
