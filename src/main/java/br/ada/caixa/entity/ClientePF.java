package br.ada.caixa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@DiscriminatorValue("PF")
public class ClientePF extends Cliente{

    private String nome;
    private LocalDate dataNascimento;

}
