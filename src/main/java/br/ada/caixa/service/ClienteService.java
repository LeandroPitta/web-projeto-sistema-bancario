package br.ada.caixa.service;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.enums.Status;
import br.ada.caixa.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClienteService {

    final private ClienteRepository clienteRepository;
    final private ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public void cadastrarClientePF(ClientePFRequestDto clientePFRequestDto) {
        ClientePF cliente = modelMapper.map(clientePFRequestDto, ClientePF.class);

        cliente.setDataNascimento(LocalDate.parse(
                clientePFRequestDto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(Status.ATIVO);

        clienteRepository.save(cliente);
    }

    public void cadastrarClientePJ(ClientePJRequestDto clientePJRequestDto) {
        ClientePJ cliente = modelMapper.map(clientePJRequestDto, ClientePJ.class);

        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(Status.ATIVO);

        clienteRepository.save(cliente);
    }

}
