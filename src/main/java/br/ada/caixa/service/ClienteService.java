package br.ada.caixa.service;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import br.ada.caixa.enums.Status;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {

    final private ModelMapper modelMapper;

    public ClienteService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClientePFResponseDto cadastrarClientePF(ClientePFRequestDto clientePFRequestDto) {
        ClientePFResponseDto cliente = modelMapper.map(clientePFRequestDto, ClientePFResponseDto.class);
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(Status.ATIVO);
        ContaResponseDto conta = new ContaResponseDto();
        conta.setId(1L);
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setDataAbertura(LocalDate.now());
        cliente.setContas(List.of(conta));
        return cliente;
    }

    public ClientePJResponseDto cadastrarClientePJ(ClientePJRequestDto clientePJRequestDto) {
        ClientePJResponseDto cliente = modelMapper.map(clientePJRequestDto, ClientePJResponseDto.class);
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(Status.ATIVO);
        ContaResponseDto conta = new ContaResponseDto();
        conta.setId(1L);
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setDataAbertura(LocalDate.now());
        cliente.setContas(List.of(conta));
        return cliente;
    }

}
