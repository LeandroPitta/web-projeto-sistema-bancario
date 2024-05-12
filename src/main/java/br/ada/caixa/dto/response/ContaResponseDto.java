package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ContaResponseDto {

    private Long id;
    private BigDecimal saldo;
    private LocalDate dataAbertura;

}
