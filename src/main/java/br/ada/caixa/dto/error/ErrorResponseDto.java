package br.ada.caixa.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponseDto {

    private String field;
    private String error;

}
