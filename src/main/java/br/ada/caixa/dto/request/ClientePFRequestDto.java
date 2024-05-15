package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ClientePFRequestDto {

    private String cpf;
    private String nome;
    //private String dataNascimento;
}
