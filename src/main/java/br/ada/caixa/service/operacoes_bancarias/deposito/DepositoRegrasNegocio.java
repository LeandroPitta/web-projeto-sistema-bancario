package br.ada.caixa.service.operacoes_bancarias.deposito;

import br.ada.caixa.dto.response.DepositoInvestimentoResponseDto;
import br.ada.caixa.dto.response.DepositoPadraoResponseDto;
import br.ada.caixa.dto.response.DepositoResponseDto;
import br.ada.caixa.entity.Conta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositoRegrasNegocio {

    private static final BigDecimal TAXA_RENDIMENTO_PF = new BigDecimal("0.01");
    private static final BigDecimal TAXA_RENDIMENTO_PJ = new BigDecimal("0.02");

    public DepositoResponseDto validarRegras(Conta conta, BigDecimal valorDeposito) {
        DepositoResponseDto depositoResponseDto;

        depositoResponseDto = rendimentoContaInvestimento(conta, valorDeposito);

        return depositoResponseDto;
    }

    private DepositoResponseDto rendimentoContaInvestimento(Conta conta, BigDecimal valorDeposito) {

        if (conta.getTipoConta().equals("INVESTIMENTO")) {
            DepositoInvestimentoResponseDto depositoResponseDto = new DepositoInvestimentoResponseDto();
            depositoResponseDto.setValorDeposito(valorDeposito);
            if (conta.getCliente().getTipoCliente().equals("PF")) {
                depositoResponseDto.setValorRendimento(depositoResponseDto.getValorDeposito().multiply(TAXA_RENDIMENTO_PF));
            } else if (conta.getCliente().getTipoCliente().equals("PJ")) {
                depositoResponseDto.setValorRendimento(depositoResponseDto.getValorDeposito().multiply(TAXA_RENDIMENTO_PJ));
            }
            depositoResponseDto.setValorTotalCredito(
                            depositoResponseDto.getValorDeposito().add(depositoResponseDto.getValorRendimento()));
            return depositoResponseDto;
        }else {
            DepositoPadraoResponseDto depositoResponseDto = new DepositoPadraoResponseDto();
            depositoResponseDto.setValorDeposito(valorDeposito);
            depositoResponseDto.setValorTotalCredito(valorDeposito);
            return depositoResponseDto;
        }
    }

}
