package br.ada.caixa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@ToString
public class ClientePFRequestDto {

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

}
