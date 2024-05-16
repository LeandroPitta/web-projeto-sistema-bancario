package br.ada.caixa.dto.request;

import br.ada.caixa.enums.TipoConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContaRequestDto {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(\\d{11}|\\d{14})$", message = "Documento do cliente deve ter 11 (CPF) ou 14 (CNPJ) caracteres numéricos")
    private String documentoCliente;

    @NotNull
    private TipoConta tipoConta;

}
