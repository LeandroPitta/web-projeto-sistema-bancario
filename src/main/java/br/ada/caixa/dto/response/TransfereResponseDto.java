package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransfereResponseDto {
    private Long numeroContaOrigem;
    private String tipoClienteOrigem;
    private BigDecimal tarifaDebito;
    private BigDecimal valorTotalDebito;
    private Long numeroContaDestino;
    private BigDecimal valorCredito;
}
