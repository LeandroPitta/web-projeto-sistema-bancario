package br.ada.caixa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INVESTIMENTO")
public class ContaInvestimento extends Conta{
}
