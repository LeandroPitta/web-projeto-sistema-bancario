package br.ada.caixa.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class ClientePJRequestDto implements ClienteRequestDto{

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

    @Override
    @Schema(hidden = true) // para o atributo não aparecer no swagger
    public String getDocumentoCliente() {
        return cnpj;
    }

    @Override
    @Schema(hidden = true) //para o atributo não aparecer no swagger
    public String getTipoCliente() {
        return "PJ";
    }

}
