package br.ada.caixa.controller;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.service.ClienteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    final private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/pf")
    public ClientePFResponseDto cadastrarClientePF(@RequestBody ClientePFRequestDto clientePFRequestDto) {
        return clienteService.cadastrarClientePF(clientePFRequestDto);
    }

    @PostMapping("/pj")
    public ClientePJResponseDto cadastrarClientePJ(@RequestBody ClientePJRequestDto clientePJRequestDto) {
        return clienteService.cadastrarClientePJ(clientePJRequestDto);
    }

    @GetMapping("/{idCliente}")
    public void pesquisarCliente() {

    }

    @GetMapping
    public void pesquisarClientes() {

    }

}
