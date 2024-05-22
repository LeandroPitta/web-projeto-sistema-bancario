package br.ada.caixa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositoRequestDto {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(\\d{11}|\\d{14})$", message = "Documento do cliente deve ter 11 (CPF) ou 14 (CNPJ) caracteres numéricos")
    private String documentoCliente;

    @NotNull
    @NotBlank
    private Long numeroConta;

    @NotNull
    @NotBlank
    private BigDecimal valor;

}
