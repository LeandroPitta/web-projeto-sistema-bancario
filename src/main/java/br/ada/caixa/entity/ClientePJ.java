package br.ada.caixa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("PJ")
public class ClientePJ extends Cliente{

    private String razaoSocial;
    private String nomeFantasia;

}
