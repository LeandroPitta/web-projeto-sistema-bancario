package br.ada.caixa.service.operacoes_bancarias.saldo;

import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaldoService {

    private final ContaRepository contaRepository;
    private final ModelMapper modelMapper;

    public ContaEClienteResponseDto consultarSaldo(Long numeroConta) {
        return contaRepository.findById(numeroConta)
                .map(conta -> modelMapper.map(conta, ContaEClienteResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Conta não encontrada"));
    }

}
