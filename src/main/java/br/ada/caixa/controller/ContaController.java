package br.ada.caixa.controller;

import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import br.ada.caixa.service.ContaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {

    final private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ContaResponseDto abrirConta(@RequestBody ContaRequestDto contaRequestDto) {
        return contaService.abrirConta(contaRequestDto);
    }

}