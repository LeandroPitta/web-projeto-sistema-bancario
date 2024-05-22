package br.ada.caixa.controller;

import br.ada.caixa.dto.request.SaqueOuDepositoRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.dto.response.DepositoResponseDto;
import br.ada.caixa.dto.response.SaqueResponseDto;
import br.ada.caixa.dto.response.TransfereResponseDto;
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
    public ResponseEntity<DepositoResponseDto> depositar(@RequestBody SaqueOuDepositoRequestDto depositoDto) {
        DepositoResponseDto deposito = operacoesBancariasService.depositar(depositoDto);
        return ResponseEntity.ok().body(deposito);
    }

    @PostMapping ("/saque")
    public ResponseEntity<SaqueResponseDto> sacar(@RequestBody SaqueOuDepositoRequestDto saqueDto) {
        SaqueResponseDto saque = operacoesBancariasService.sacar(saqueDto);
        return ResponseEntity.ok().body(saque);
    }

    @PostMapping("/transfere")
    public ResponseEntity<TransfereResponseDto> transferir(@RequestBody TransfereRequestDto transfereRequestDto) {
        TransfereResponseDto transfereResponseDto = operacoesBancariasService.transferir(transfereRequestDto);
        return ResponseEntity.ok().body(transfereResponseDto);
    }

    @GetMapping("/saldo/{numeroConta}")
    public ResponseEntity<ContaEClienteResponseDto> consultarSaldo(@PathVariable Long numeroConta) {
        return ResponseEntity.ok().body(operacoesBancariasService.consultarSaldo(numeroConta));
    }

}
