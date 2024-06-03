package br.ada.caixa.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ClienteResponsePageDto {

    private long total;
    private long totalPages;
    private int page;
    private int size;

    private List<ClienteResponseDto> content;

}
