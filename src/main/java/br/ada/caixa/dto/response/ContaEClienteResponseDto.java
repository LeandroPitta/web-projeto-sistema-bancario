package br.ada.caixa.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContaEClienteResponseDto {

    private Long numeroConta;
    private BigDecimal saldo;
    private LocalDate dataAbertura;
    private String tipoConta;
    private ClienteSimplificadoResponseDto cliente;

}
