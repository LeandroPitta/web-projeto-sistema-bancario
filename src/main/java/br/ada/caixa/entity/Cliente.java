package br.ada.caixa.entity;

import br.ada.caixa.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_pessoa", discriminatorType = DiscriminatorType.STRING, length = 10)
public class Cliente {

    @Id
    private String documento;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dataCadastro;

}
