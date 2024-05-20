package br.ada.caixa.dto.filter;

import br.ada.caixa.enums.Status;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFilterDto {

    @Pattern(regexp = "^(PF|PJ)?$", message = "O Tipo do cliente deve ser PF ou PJ")
    private String tipoCliente;

    private String nome;

    @Pattern(regexp = "^(\\d{11}|\\d{14})$", message = "Documento do cliente deve ter 11 (CPF) ou 14 (CNPJ) caracteres numéricos")
    private String documentoCliente;

}
