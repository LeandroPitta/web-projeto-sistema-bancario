package br.ada.caixa.controller;

import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.InvestirRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.SaldoResponseDto;
import br.ada.caixa.service.OperacoesBancariasService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operacoes")
public class OperacoesBancariasController {

    final private OperacoesBancariasService operacoesBancariasService;

    public OperacoesBancariasController(OperacoesBancariasService operacoesBancariasService) {
        this.operacoesBancariasService = operacoesBancariasService;
    }

    @PostMapping("/deposito")
    public void depositar(@RequestBody DepositoRequestDto depositoRequestDto) {

        operacoesBancariasService.depositar(depositoRequestDto);

    }

    @PostMapping ("/saque")
    public void sacar(@RequestBody SaqueRequestDto saqueRequestDto) {

        operacoesBancariasService.sacar(saqueRequestDto);

    }

    @PostMapping("/transfere")
    public void transferir(@RequestBody TransfereRequestDto transfereRequestDto) {

        operacoesBancariasService.transferir(transfereRequestDto);

    }

    @GetMapping("/saldo/{idConta}")
    public SaldoResponseDto consultarSaldo(@PathVariable String idConta) {

        return operacoesBancariasService.consultarSaldo(idConta);

    }

    @PostMapping("/investimento")
    public void investir(@RequestBody InvestirRequestDto investirRequestDto) {

        operacoesBancariasService.investir(investirRequestDto);

    }

}
