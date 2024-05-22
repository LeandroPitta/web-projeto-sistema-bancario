package br.ada.caixa.service.regrasnegocio;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ContaRepository;

public class VerificacaoTitularContaCliente {

    public static Conta verificar(ContaRepository contaRepository, Long conta, String documentoCliente) {
        return contaRepository.findById(conta).map(c -> {
                    if (c.getCliente().getDocumentoCliente().equals(documentoCliente))
                        return c;
                    throw new ValidacaoException("O cliente indicado não é titular da conta");
                })
                .orElseThrow(() -> new ValidacaoException("Conta não encontrada"));
    }
}
