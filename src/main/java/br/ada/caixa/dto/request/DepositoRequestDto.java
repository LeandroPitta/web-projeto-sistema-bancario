package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DepositoRequestDto {

    private String numeroConta;
    private BigDecimal valor;

}
