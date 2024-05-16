package br.ada.caixa.dto.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFilterDto {

    private String tipoCliente;
    private String nome;
    private String documento;
    private String status;

}
