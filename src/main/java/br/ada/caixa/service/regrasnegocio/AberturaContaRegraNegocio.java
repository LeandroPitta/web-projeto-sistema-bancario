package br.ada.caixa.service.regrasnegocio;

import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.exceptions.ValidacaoException;

public class AberturaContaRegraNegocio {

    public static void validarRegras(Cliente cliente, ContaRequestDto contaRequestDto) {
        poupancaApenasParaPF(cliente.getTipoCliente(), contaRequestDto.getTipoConta());
        // Se houver mais regras de negócio, adicione aqui
    }

    private static void poupancaApenasParaPF(String tipoCliente, String tipoConta) {
        if (!tipoCliente.equals("PF") && tipoConta.equals("POUPANCA")) {
            throw new ValidacaoException("Apenas clientes PF podem abrir conta POUPANCA");
        }
    }

}
