package br.ada.caixa.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class ClientePFRequestDto implements ClienteRequestDto{

    @NotNull
    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @NotBlank
    @Size(min = 3, message = "Razão Social deve ter pelo menos 3 caracteres")
    private String nome;

    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Data de nascimento deve estar no formato yyyy-MM-dd")
    private String dataNascimento;

    @Override
    @Schema(hidden = true) //para o atributo não aparecer no swagger
    public String getDocumentoCliente() {
        return cpf;
    }

    @Override
    @Schema(hidden = true) //para o atributo não aparecer no swagger
    public String getTipoCliente() {
        return "PF";
    }

}
