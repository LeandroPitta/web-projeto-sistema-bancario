package br.ada.caixa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

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
    private Long numeroConta;

    @Column(name = "tipo_conta", insertable = false, updatable = false)
    private String tipoConta;

    private BigDecimal saldo;

    private LocalDate dataAbertura;

    @ManyToOne
    @JoinColumn(name="documentoCliente", referencedColumnName="documentoCliente", nullable=false)
    private Cliente cliente;

}
