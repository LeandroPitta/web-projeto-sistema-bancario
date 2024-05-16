package br.ada.caixa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@ToString
public class ClientePJRequestDto {

    @NotNull
    @NotBlank
    @CNPJ
    private String cnpj;

    @NotNull
    @NotBlank
    @Size(min = 3, message = "Razão Social deve ter pelo menos 3 caracteres")
    private String razaoSocial;

    @NotNull
    @NotBlank
    @Size(min = 3, message = "Razão Social deve ter pelo menos 3 caracteres")
    private String nomeFantasia;

}
