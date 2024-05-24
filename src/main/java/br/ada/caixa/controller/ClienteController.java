package br.ada.caixa.controller;

import br.ada.caixa.dto.filter.ClienteFilterDto;
import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.dto.response.ClienteResponsePageDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.service.cliente.CadastroClienteService;
import br.ada.caixa.service.cliente.PesquisaClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    final private CadastroClienteService cadastroClienteService;
    final private PesquisaClienteService pesquisaClienteService;

    public ClienteController(CadastroClienteService cadastroClienteService, PesquisaClienteService pesquisaClienteService) {
        this.cadastroClienteService = cadastroClienteService;
        this.pesquisaClienteService = pesquisaClienteService;
    }

    @PostMapping("/cadastro/pf")
    public ResponseEntity<?> cadastrarClientePF(@RequestBody @Valid ClientePFRequestDto clientePFRequestDto) {
        cadastroClienteService.cadastrarCliente(clientePFRequestDto, ClientePF.class);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/cadastro/pj")
    public void cadastrarClientePJ(@RequestBody @Valid ClientePJRequestDto clientePJRequestDto) {
        cadastroClienteService.cadastrarCliente(clientePJRequestDto, ClientePJ.class);
    }

    @GetMapping("/{documentoCliente}")
    public ClienteResponseDto pesquisarCliente(@PathVariable String documentoCliente) {
        return pesquisaClienteService.pesquisarCliente(documentoCliente);
    }

    @GetMapping
    public ClienteResponsePageDto pesquisarClientes(@Valid ClienteFilterDto filter,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "" + Integer.MAX_VALUE) int size) {
        return pesquisaClienteService.pesquisarClientes(filter, page, size);
    }

}
