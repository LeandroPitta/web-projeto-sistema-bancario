package br.ada.caixa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@DiscriminatorValue("PF")
public class ClientePF extends Cliente{

    private String nome;
    private LocalDate dataNascimento;

}
