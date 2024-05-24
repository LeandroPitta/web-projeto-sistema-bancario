package br.ada.caixa.controller;

import br.ada.caixa.dto.request.SaqueOuDepositoRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.dto.response.DepositoResponseDto;
import br.ada.caixa.dto.response.SaqueResponseDto;
import br.ada.caixa.dto.response.TransfereResponseDto;
import br.ada.caixa.service.operacoes_bancarias.deposito.DepositoService;
import br.ada.caixa.service.operacoes_bancarias.saldo.SaldoService;
import br.ada.caixa.service.operacoes_bancarias.saque.SaqueService;
import br.ada.caixa.service.operacoes_bancarias.transfere.TransfereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operacoes")
public class OperacoesBancariasController {

    final private SaldoService saldoService;
    final private SaqueService saqueService;
    final private DepositoService depositoService;
    final private TransfereService transfereService;

    public OperacoesBancariasController(TransfereService transfereService, DepositoService depositoService,
                                        SaqueService saqueService, SaldoService saldoService) {
        this.transfereService = transfereService;
        this.depositoService = depositoService;
        this.saqueService = saqueService;
        this.saldoService = saldoService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<DepositoResponseDto> depositar(@RequestBody SaqueOuDepositoRequestDto depositoDto) {
        DepositoResponseDto deposito = depositoService.depositar(depositoDto);
        return ResponseEntity.ok().body(deposito);
    }

    @PostMapping ("/saque")
    public ResponseEntity<SaqueResponseDto> sacar(@RequestBody SaqueOuDepositoRequestDto saqueDto) {
        SaqueResponseDto saque = saqueService.sacar(saqueDto);
        return ResponseEntity.ok().body(saque);
    }

    @PostMapping("/transfere")
    public ResponseEntity<TransfereResponseDto> transferir(@RequestBody TransfereRequestDto transfereRequestDto) {
        TransfereResponseDto transfereResponseDto = transfereService.transferir(transfereRequestDto);
        return ResponseEntity.ok().body(transfereResponseDto);
    }

    @GetMapping("/saldo/{numeroConta}")
    public ResponseEntity<ContaEClienteResponseDto> consultarSaldo(@PathVariable Long numeroConta) {
        return ResponseEntity.ok().body(saldoService.consultarSaldo(numeroConta));
    }

}
