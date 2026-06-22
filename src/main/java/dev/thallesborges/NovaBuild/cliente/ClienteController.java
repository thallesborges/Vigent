package dev.thallesborges.NovaBuild.cliente;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse cadastrarCliente(@Valid @RequestBody CadastrarClienteRequest request) {
        return clienteService.cadastrarCliente(request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponse> listarClientes() {
        return clienteService.listarClientes();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponse updateCliente (@Valid @PathVariable Long id,
                                          @RequestBody CadastrarClienteRequest request) {
        return clienteService.updateCliente(id, request);
    }
}

