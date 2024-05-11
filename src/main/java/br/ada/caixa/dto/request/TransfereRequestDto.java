package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransfereRequestDto {

    private String idContaOrigem;
    private String idContaDestino;
    private Double valor;

}
