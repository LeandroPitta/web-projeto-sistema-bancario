package br.ada.caixa.entity;

import br.ada.caixa.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class ClientePJ {
    @Id
    private String cnpj;
    private String razaoSocial;
    private LocalDate dataCadastro;
    private Status status;
}
