package br.ada.caixa.service.operacoes_bancarias.transfere;

import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.DepositoResponseDto;
import br.ada.caixa.dto.response.SaqueResponseDto;
import br.ada.caixa.dto.response.TransfereResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.repository.ContaRepository;
import br.ada.caixa.service.operacoes_bancarias.deposito.DepositoRegrasNegocio;
import br.ada.caixa.service.operacoes_bancarias.saque.SaqueRegrasNegocio;
import br.ada.caixa.service.util.VerificacaoTitularContaCliente;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransfereService {

    private final ContaRepository contaRepository;
    private final SaqueRegrasNegocio saqueRegrasNegocio;
    private final DepositoRegrasNegocio depositoRegrasNegocio;
    private final VerificacaoTitularContaCliente verificacaoTitularContaCliente;

    public TransfereResponseDto transferir(TransfereRequestDto transfereRequestDto) {

        Conta contaOrigem = verificacaoTitularContaCliente.verificar(
                contaRepository, transfereRequestDto.getNumeroContaOrigem(), transfereRequestDto.getDocumentoClienteOrigem());
        Conta contaDestino = verificacaoTitularContaCliente.verificar(
                contaRepository, transfereRequestDto.getNumeroContaDestino(), transfereRequestDto.getDocumentoClienteDestino());

        SaqueResponseDto saqueResponseDto = saqueRegrasNegocio.validarRegras(contaOrigem, transfereRequestDto.getValor());
        DepositoResponseDto depositoResponseDto = depositoRegrasNegocio.validarRegras(contaDestino, transfereRequestDto.getValor());

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(saqueResponseDto.getValorTotalDebito()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(depositoResponseDto.getValorTotalCredito()));
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        TransfereResponseDto transfereResponseDto = new TransfereResponseDto();
        transfereResponseDto.setNumeroContaOrigem(contaOrigem.getNumeroConta());
        transfereResponseDto.setTipoClienteOrigem(contaOrigem.getCliente().getTipoCliente());
        transfereResponseDto.setTarifaDebito(saqueResponseDto.getTaxaSaque());
        transfereResponseDto.setValorTotalDebito(saqueResponseDto.getValorTotalDebito());
        transfereResponseDto.setNumeroContaDestino(contaDestino.getNumeroConta());
        transfereResponseDto.setValorCredito(transfereRequestDto.getValor());

        return transfereResponseDto;
    }

}
