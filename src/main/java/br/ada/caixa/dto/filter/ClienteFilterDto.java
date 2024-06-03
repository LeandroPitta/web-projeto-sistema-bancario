package br.ada.caixa.dto.filter;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClienteFilterDto {

    @Pattern(regexp = "^(PF|PJ)?$", message = "O Tipo do cliente deve ser PF ou PJ")
    private String tipoCliente;

    private String nome;

}
