package br.ada.caixa.controller;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    final private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/pf")
    public ResponseEntity<?> cadastrarClientePF(@RequestBody @Valid ClientePFRequestDto clientePFRequestDto) {
        clienteService.cadastrarClientePF(clientePFRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/pj")
    public void cadastrarClientePJ(@RequestBody ClientePJRequestDto clientePJRequestDto) {
        clienteService.cadastrarClientePJ(clientePJRequestDto);
    }

    @GetMapping("/{documentoCliente}")
    public ClienteResponseDto pesquisarCliente(@PathVariable String documentoCliente) {
        return clienteService.pesquisarCliente(documentoCliente);
    }

    @GetMapping
    public void pesquisarClientes() {

    }

}
