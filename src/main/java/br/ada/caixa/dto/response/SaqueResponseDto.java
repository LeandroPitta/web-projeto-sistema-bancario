package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaqueResponseDto {
    BigDecimal valorSaque;
    BigDecimal taxaSaque;
    BigDecimal valorTotalDebito;
    BigDecimal saldoAtual;
}
