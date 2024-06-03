package br.ada.caixa.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositoPadraoResponseDto implements DepositoResponseDto{
    BigDecimal valorDeposito;
    BigDecimal valorTotalCredito;
    BigDecimal novoSaldo;
}
