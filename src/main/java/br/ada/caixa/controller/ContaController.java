package br.ada.caixa.controller;

import br.ada.caixa.dto.filter.ContaFilterDto;
import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import br.ada.caixa.dto.response.ContaResponsePageDto;
import br.ada.caixa.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    final private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public void abrirConta(@RequestBody @Valid ContaRequestDto contaRequestDto) {
        contaService.abrirConta(contaRequestDto);
    }

    @GetMapping("/{numeroConta}")
    public ContaEClienteResponseDto pesquisarConta(@PathVariable Long numeroConta) {
        return contaService.pesquisarConta(numeroConta);
    }

    @GetMapping
    public ContaResponsePageDto pesquisarContas(@Valid ContaFilterDto filter,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "" + Integer.MAX_VALUE) int size) {
        return contaService.pesquisarContas(filter, page, size);
    }

}
