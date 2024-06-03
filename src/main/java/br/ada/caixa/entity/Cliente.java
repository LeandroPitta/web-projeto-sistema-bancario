package br.ada.caixa.entity;

import br.ada.caixa.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_cliente", discriminatorType = DiscriminatorType.STRING, length = 10)
public class Cliente {

    @Id
    private String documentoCliente;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dataCadastro;

    @Column(name = "tipo_cliente", insertable = false, updatable = false)
    private String tipoCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Conta> contas;

}
