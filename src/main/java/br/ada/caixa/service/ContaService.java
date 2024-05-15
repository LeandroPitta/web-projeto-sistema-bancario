package br.ada.caixa.service;

import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.factory.ContaFactory;
import br.ada.caixa.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ContaService {

    final private ContaRepository contaRepository;
    final private ModelMapper modelMapper;
    final private ContaFactory contaFactory;

    public ContaService(ContaRepository contaRepository, ModelMapper modelMapper, ContaFactory contaFactory) {
        this.contaRepository = contaRepository;
        this.modelMapper = modelMapper;
        this.contaFactory = contaFactory;
    }

    public void abrirConta(ContaRequestDto contaRequestDto) {
        Conta instanciaConta = contaFactory.getConta(contaRequestDto.getTipoConta());
        Conta conta = modelMapper.map(contaRequestDto, instanciaConta.getClass());
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setDataAbertura(LocalDate.now());
        contaRepository.save(conta);
    }
}
