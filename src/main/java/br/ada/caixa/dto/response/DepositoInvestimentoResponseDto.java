package br.ada.caixa.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositoInvestimentoResponseDto implements DepositoResponseDto{
    BigDecimal valorDeposito;
    BigDecimal valorRendimento;
    BigDecimal valorTotalCredito;
    BigDecimal novoSaldo;
}
