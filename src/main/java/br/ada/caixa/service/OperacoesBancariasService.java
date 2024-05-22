package br.ada.caixa.service;

import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ContaRepository;
import br.ada.caixa.service.regrasnegocio.VerificacaoTitularContaCliente;
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

    public void depositar(DepositoRequestDto depositoRequestDto) {

        Conta conta = VerificacaoTitularContaCliente.verificar(
                contaRepository, depositoRequestDto.getNumeroConta(), depositoRequestDto.getDocumentoCliente());
        conta.setSaldo(conta.getSaldo().add(depositoRequestDto.getValor()));
        contaRepository.save(conta);
    }

    public void sacar(SaqueRequestDto saqueRequestDto) {

        System.out.println(saqueRequestDto.toString());

    }

    public void transferir(TransfereRequestDto transfereRequestDto) {

        System.out.println(transfereRequestDto.toString());

    }

    public ContaEClienteResponseDto consultarSaldo(Long numeroConta) {
        return contaRepository.findById(numeroConta)
                .map(conta -> modelMapper.map(conta, ContaEClienteResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Conta não encontrada"));
    }

}
