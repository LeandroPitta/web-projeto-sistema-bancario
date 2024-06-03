package br.ada.caixa.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContaResponseDto {

    private Long numeroConta;
    private BigDecimal saldo;
    private LocalDate dataAbertura;
    private String tipoConta;

}
