package br.ada.caixa.dto.request;

import br.ada.caixa.enums.TipoConta;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContaRequestDto {
    private String idCliente;
    private TipoConta tipoConta;
}
