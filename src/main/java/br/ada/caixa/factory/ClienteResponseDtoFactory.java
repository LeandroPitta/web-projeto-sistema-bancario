package br.ada.caixa.factory;

import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.exceptions.ValidacaoException;

//Melhor pratica dessa forma ou o metodo no DTO?
public class ClienteResponseDtoFactory {
    public ClienteResponseDto getClienteResponseDto(String tipoCliente) {
        switch (tipoCliente) {
            case "PF":
                return new ClientePFResponseDto();
            case "PJ":
                return new ClientePJResponseDto();
            default:
                throw new ValidacaoException("Tipo de cliente inválido");
        }
    }
}
