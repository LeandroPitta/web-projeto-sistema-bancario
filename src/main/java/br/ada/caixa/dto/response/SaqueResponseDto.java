package br.ada.caixa.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaqueResponseDto {
    BigDecimal valorSaque;
    BigDecimal taxaSaque;
    BigDecimal valorTotalDebito;
    BigDecimal saldoAtual;
}
