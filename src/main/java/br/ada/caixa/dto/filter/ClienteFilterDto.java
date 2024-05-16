package br.ada.caixa.dto.filter;

import br.ada.caixa.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFilterDto {

    private String tipoCliente;
    private String nome;
    private String documentoCliente;

}
