package br.ada.caixa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContaResponsePageDto {

    private long total;
    private long totalPages;
    private int page;
    private int size;

    private List<ContaResponseDto> content;

}
