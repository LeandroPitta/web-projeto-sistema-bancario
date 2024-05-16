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
public class ClientePFResponseDto implements ClienteResponseDto{
    private String cpf;
    private String nome;
    private LocalDate dataCadastro;
    private Status status;
    private List<ContaResponseDto> contas;
}
