package br.ada.caixa.factory;

import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.dto.response.ClienteResponseDto;

public class ClienteResponseDtoFactory {
    public ClienteResponseDto getClienteResponseDto(String instanciaCliente) {
        switch (instanciaCliente) {
            case "ClientePF":
                return new ClientePFResponseDto();
            case "ClientePJ":
                return new ClientePJResponseDto();
            default:
                throw new IllegalArgumentException("Tipo de cliente inválido");
        }
    }
}
