package br.ada.caixa.service.regrasnegocio;

import br.ada.caixa.dto.request.SaqueOuDepositoRequestDto;
import br.ada.caixa.dto.response.SaqueResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.exceptions.ValidacaoException;

import java.math.BigDecimal;

public class SaqueRegrasNegocio {

    private static final BigDecimal TAXA_SAQUE_PJ = new BigDecimal("0.005");

    public static SaqueResponseDto validarRegras(Conta conta, BigDecimal valorSaque) {
        SaqueResponseDto saqueResponseDto;

        saldoSuficiente(conta, valorSaque);
        saqueResponseDto = cobrarTaxaClientePJ(conta, valorSaque);

        return saqueResponseDto;
    }

    private static void saldoSuficiente(Conta conta, BigDecimal valorSaque) {
        if (conta.getSaldo().compareTo(valorSaque) < 0) {
            throw new ValidacaoException("Saldo insuficiente");
        }
    }

    private static SaqueResponseDto cobrarTaxaClientePJ(Conta conta, BigDecimal valorSaque) {

        SaqueResponseDto saqueResponseDto = new SaqueResponseDto();
        if (conta.getCliente().getTipoCliente().equals("PJ") && conta.getTipoConta().equals("CORRENTE")) {
            saqueResponseDto.setValorSaque(valorSaque);
            saqueResponseDto.setTaxaSaque(valorSaque.multiply(TAXA_SAQUE_PJ));
            saqueResponseDto.setValorTotalDebito(valorSaque.add(saqueResponseDto.getTaxaSaque()));
        }else {
            saqueResponseDto.setValorSaque(valorSaque);
            saqueResponseDto.setTaxaSaque(BigDecimal.ZERO);
            saqueResponseDto.setValorTotalDebito(valorSaque);
        }
        if (conta.getSaldo().compareTo(saqueResponseDto.getValorTotalDebito()) < 0) {
            throw new ValidacaoException("Saldo insuficiente");
        }
        return saqueResponseDto;
    }
}
