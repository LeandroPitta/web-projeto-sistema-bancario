package br.ada.caixa.controller;

import br.ada.caixa.dto.request.DepositoRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operacoes")
public class OperacoesBancariasController {

    @PostMapping("/deposito")
    public void depositar(@RequestBody DepositoRequestDto depositoRequestDto) {

        System.out.println(depositoRequestDto);

    }

    @PostMapping ("/saque")
    public void sacar() {}

    @PostMapping("/transfere")
    public void transferir() {}

    @GetMapping("/saldo")
    public void consultarSaldo() {}

    @PostMapping("/investimento")
    public void investir() {}

}
