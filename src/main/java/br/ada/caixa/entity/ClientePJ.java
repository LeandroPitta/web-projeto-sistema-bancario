package br.ada.caixa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("PJ")
public class ClientePJ extends Cliente{

    private String razaoSocial;
    private String nomeFantasia;

}
