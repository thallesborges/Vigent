package dev.thallesborges.NovaBuild.controller;

import dev.thallesborges.NovaBuild.dto.request.CadastrarPlanoRequest;
import dev.thallesborges.NovaBuild.dto.response.PlanoResponse;
import dev.thallesborges.NovaBuild.service.PlanoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
