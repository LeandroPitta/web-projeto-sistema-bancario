package br.ada.caixa.dto.response;

import java.math.BigDecimal;

public interface DepositoResponseDto {

    BigDecimal getValorTotalCredito();
    void setNovoSaldo(BigDecimal saldo);
}
