package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositoPadraoResponseDto implements DepositoResponseDto{
    BigDecimal valorDeposito;
    BigDecimal valorTotalCredito;
    BigDecimal novoSaldo;
}
