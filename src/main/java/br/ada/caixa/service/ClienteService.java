package br.ada.caixa.service;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.enums.Status;
import br.ada.caixa.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {

    final private ClienteRepository clienteRepository;
    final private ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(ClientePFRequestDto.class, ClientePF.class)
                .addMapping(ClientePFRequestDto::getCpf, ClientePF::setDocumento);
    }

    public void cadastrarClientePF(ClientePFRequestDto clientePFRequestDto) {
        ClientePF cliente = modelMapper.map(clientePFRequestDto, ClientePF.class);
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(Status.ATIVO);
        clienteRepository.save(cliente);
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
