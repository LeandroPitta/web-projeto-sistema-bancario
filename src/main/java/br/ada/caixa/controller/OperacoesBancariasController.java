package br.ada.caixa.controller;

import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.service.OperacoesBancariasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operacoes")
public class OperacoesBancariasController {

    final private OperacoesBancariasService operacoesBancariasService;

    public OperacoesBancariasController(OperacoesBancariasService operacoesBancariasService) {
        this.operacoesBancariasService = operacoesBancariasService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<?> depositar(@RequestBody DepositoRequestDto depositoRequestDto) {
        operacoesBancariasService.depositar(depositoRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping ("/saque")
    public void sacar(@RequestBody SaqueRequestDto saqueRequestDto) {
        operacoesBancariasService.sacar(saqueRequestDto);
    }

    @PostMapping("/transfere")
    public void transferir(@RequestBody TransfereRequestDto transfereRequestDto) {
        operacoesBancariasService.transferir(transfereRequestDto);
    }

    @GetMapping("/saldo/{numeroConta}")
    public ResponseEntity<ContaEClienteResponseDto> consultarSaldo(@PathVariable Long numeroConta) {
        return ResponseEntity.ok().body(operacoesBancariasService.consultarSaldo(numeroConta));
    }

}
