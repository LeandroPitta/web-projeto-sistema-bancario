package br.ada.caixa.factory;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.ContaCorrente;
import br.ada.caixa.entity.ContaInvestimento;
import br.ada.caixa.entity.ContaPoupanca;
import br.ada.caixa.enums.TipoConta;
import br.ada.caixa.exceptions.ValidacaoException;

public class ContaFactory {
    public Conta getConta(TipoConta tipoConta) {
        switch (tipoConta) {
            case CORRENTE:
                return new ContaCorrente();
            case POUPANCA:
                return new ContaPoupanca();
            case INVESTIMENTO:
                return new ContaInvestimento();
            default:
                throw new ValidacaoException("Tipo de conta inválido");
        }
    }
}
