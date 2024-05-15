package br.ada.caixa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente  extends Conta{
}
