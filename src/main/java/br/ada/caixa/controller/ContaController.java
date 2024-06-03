package br.ada.caixa.controller;

import br.ada.caixa.dto.filter.ContaFilterDto;
import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.dto.response.ContaResponsePageDto;
import br.ada.caixa.service.conta.AberturaContaService;
import br.ada.caixa.service.conta.PesquisaContaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    final private AberturaContaService AberturaContaService;
    final private PesquisaContaService pesquisaContaService;

    public ContaController(AberturaContaService aberturaContaService,
                           PesquisaContaService pesquisaContaService) {
        this.AberturaContaService = aberturaContaService;
        this.pesquisaContaService = pesquisaContaService;
    }

    @PostMapping
    public void abrirConta(@RequestBody @Valid ContaRequestDto contaRequestDto) {
        AberturaContaService.abrirConta(contaRequestDto);
    }

    @GetMapping("/{numeroConta}")
    public ContaEClienteResponseDto pesquisarConta(@PathVariable Long numeroConta) {
        return pesquisaContaService.pesquisarConta(numeroConta);
    }

    @GetMapping
    public ContaResponsePageDto pesquisarContas(@Valid ContaFilterDto filter,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "" + Integer.MAX_VALUE) int size) {
        return pesquisaContaService.pesquisarContas(filter, page, size);
    }

}
