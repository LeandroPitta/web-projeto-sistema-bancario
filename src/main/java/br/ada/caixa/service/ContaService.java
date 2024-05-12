package br.ada.caixa.service;

import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ContaService {

    final private ModelMapper modelMapper;

    public ContaService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ContaResponseDto abrirConta(ContaRequestDto contaRequestDto) {
        ContaResponseDto conta = modelMapper.map(contaRequestDto, ContaResponseDto.class);
        conta.setId(1L);
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setDataAbertura(LocalDate.now());
        System.out.println(contaRequestDto.toString() + conta.toString());
        return conta;
    }
}
