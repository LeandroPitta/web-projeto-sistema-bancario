package br.ada.caixa.service.operacoes_bancarias.saldo;

import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {

    final private ContaRepository contaRepository;
    final private ModelMapper modelMapper;

    public SaldoService(ContaRepository contaRepository, ModelMapper modelMapper) {
        this.contaRepository = contaRepository;
        this.modelMapper = modelMapper;
    }

    public ContaEClienteResponseDto consultarSaldo(Long numeroConta) {
        return contaRepository.findById(numeroConta)
                .map(conta -> modelMapper.map(conta, ContaEClienteResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Conta não encontrada"));
    }

}
