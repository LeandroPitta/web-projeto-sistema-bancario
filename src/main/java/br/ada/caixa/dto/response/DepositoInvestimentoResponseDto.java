package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositoInvestimentoResponseDto implements DepositoResponseDto{
    BigDecimal valorDeposito;
    BigDecimal valorRendimento;
    BigDecimal valorTotalCredito;
    BigDecimal novoSaldo;
}
