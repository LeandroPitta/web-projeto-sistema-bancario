package br.ada.caixa.controller;

import br.ada.caixa.dto.request.ContaRequestDto;
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
    public ContaResponseDto pesquisarConta(@PathVariable Long numeroConta) {
        return contaService.pesquisarConta(numeroConta);
    }

}
