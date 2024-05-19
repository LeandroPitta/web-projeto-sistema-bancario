package br.ada.caixa.dto.response;

import br.ada.caixa.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class ClienteSimplificadoResponseDto{
    private String documentoCliente;
    private String nome;
    private LocalDate dataCadastro;
    private String tipoCliente;
    private Status status;
}
