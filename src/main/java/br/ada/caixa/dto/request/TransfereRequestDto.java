package br.ada.caixa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransfereRequestDto {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(\\d{11}|\\d{14})$", message = "Documento do cliente deve ter 11 (CPF) ou 14 (CNPJ) caracteres numéricos")
    private String documentoClienteOrigem;

    @NotNull
    @NotBlank
    private Long numeroContaOrigem;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(\\d{11}|\\d{14})$", message = "Documento do cliente deve ter 11 (CPF) ou 14 (CNPJ) caracteres numéricos")
    private String documentoClienteDestino;

    @NotNull
    @NotBlank
    private Long numeroContaDestino;

    @NotNull
    @NotBlank
    private BigDecimal valor;

}
