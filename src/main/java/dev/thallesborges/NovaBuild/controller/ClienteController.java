package dev.thallesborges.NovaBuild.controller;

import dev.thallesborges.NovaBuild.dto.request.CadastrarClienteRequest;
import dev.thallesborges.NovaBuild.dto.response.ClienteResponse;
import dev.thallesborges.NovaBuild.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse cadastrarCliente(@Valid @RequestBody CadastrarClienteRequest cadastrarClienteRequest) {
        return clienteService.cadastrarCliente(cadastrarClienteRequest);
    }
}

