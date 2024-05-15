package br.ada.caixa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 15)
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String idCliente;

    private BigDecimal saldo;
    private LocalDate dataAbertura;
}
