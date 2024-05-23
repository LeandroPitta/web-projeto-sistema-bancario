package br.ada.caixa.service;

import br.ada.caixa.dto.request.SaqueOuDepositoRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.dto.response.DepositoResponseDto;
import br.ada.caixa.dto.response.SaqueResponseDto;
import br.ada.caixa.dto.response.TransfereResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ContaRepository;
import br.ada.caixa.service.regras_negocio.DepositoRegrasNegocio;
import br.ada.caixa.service.regras_negocio.SaqueRegrasNegocio;
import br.ada.caixa.service.util.VerificacaoTitularContaCliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OperacoesBancariasService {

    final private ModelMapper modelMapper;
    final private ContaRepository contaRepository;

    public OperacoesBancariasService(ModelMapper modelMapper, ContaRepository contaRepository) {
        this.modelMapper = modelMapper;
        this.contaRepository = contaRepository;
    }

    public DepositoResponseDto depositar(SaqueOuDepositoRequestDto depositoRequestDto) {

        Conta conta = VerificacaoTitularContaCliente.verificar(
                contaRepository, depositoRequestDto.getNumeroConta(), depositoRequestDto.getDocumentoCliente());

        DepositoResponseDto depositoResponseDto = DepositoRegrasNegocio.validarRegras(conta, depositoRequestDto.getValor());

        conta.setSaldo(conta.getSaldo().add(depositoResponseDto.getValorTotalCredito()));
        contaRepository.save(conta);
        contaRepository.findById(depositoRequestDto.getNumeroConta()).ifPresent(contaAtualizada -> {
            depositoResponseDto.setNovoSaldo(contaAtualizada.getSaldo());
        });

        return depositoResponseDto;
    }

    public SaqueResponseDto sacar(SaqueOuDepositoRequestDto saqueRequestDto) {

        Conta conta = VerificacaoTitularContaCliente.verificar(
                contaRepository, saqueRequestDto.getNumeroConta(), saqueRequestDto.getDocumentoCliente());

        SaqueResponseDto saqueResponseDto = SaqueRegrasNegocio.validarRegras(conta, saqueRequestDto.getValor());

        conta.setSaldo(conta.getSaldo().subtract(saqueResponseDto.getValorTotalDebito()));
        contaRepository.save(conta);
        contaRepository.findById(saqueRequestDto.getNumeroConta()).ifPresent(contaAtualizada -> {
            saqueResponseDto.setSaldoAtual(contaAtualizada.getSaldo());
        });

        return saqueResponseDto;
    }

    public TransfereResponseDto transferir(TransfereRequestDto transfereRequestDto) {

        Conta contaOrigem = VerificacaoTitularContaCliente.verificar(
                contaRepository, transfereRequestDto.getNumeroContaOrigem(), transfereRequestDto.getDocumentoClienteOrigem());
        Conta contaDestino = VerificacaoTitularContaCliente.verificar(
                contaRepository, transfereRequestDto.getNumeroContaDestino(), transfereRequestDto.getDocumentoClienteDestino());

        SaqueResponseDto saqueResponseDto = SaqueRegrasNegocio.validarRegras(contaOrigem, transfereRequestDto.getValor());
        DepositoResponseDto depositoResponseDto = DepositoRegrasNegocio.validarRegras(contaDestino, transfereRequestDto.getValor());

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

    public ContaEClienteResponseDto consultarSaldo(Long numeroConta) {
        return contaRepository.findById(numeroConta)
                .map(conta -> modelMapper.map(conta, ContaEClienteResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Conta não encontrada"));
    }

}
