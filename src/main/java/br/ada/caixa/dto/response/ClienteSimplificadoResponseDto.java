package br.ada.caixa.dto.response;

import br.ada.caixa.enums.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteSimplificadoResponseDto{
    private String documentoCliente;
    private String nome;
    private LocalDate dataCadastro;
    private String tipoCliente;
    private Status status;
}
