package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientePJRequestDto {
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
}
