package br.ada.caixa.entity;

import br.ada.caixa.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class ClientePF {
    @Id
    private String cpf;
    private String nome;
    private LocalDate dataCadastro;
    private Status status;
}
