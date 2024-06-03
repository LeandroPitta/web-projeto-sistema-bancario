package br.ada.caixa.dto.filter;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ContaFilterDto {

    @Pattern(regexp = "^(CORRENTE|POUPANCA|INVESTIMENTO)?$", message = "O tipo da conta deve ser CORRENTE, POUPANCA ou INVESTIMENTO")
    private String tipoConta;

    @Pattern(regexp = "^(PF|PJ)?$", message = "O Tipo do cliente deve ser PF ou PJ")
    private String tipoCliente;

}
