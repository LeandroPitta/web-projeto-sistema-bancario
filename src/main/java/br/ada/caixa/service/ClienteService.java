package br.ada.caixa.service;

import br.ada.caixa.dto.filter.ClienteFilterDto;
import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.dto.response.ClienteResponsePageDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.enums.Status;
import br.ada.caixa.factory.ClienteResponseDtoFactory;
import br.ada.caixa.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    final private ClienteRepository clienteRepository;
    final private ModelMapper modelMapper;
    final private ClienteResponseDtoFactory clienteResponseDtoFactory;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.clienteResponseDtoFactory = new ClienteResponseDtoFactory();
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

    public ClienteResponseDto pesquisarCliente(String documentoCliente) {
        return clienteRepository.findById(documentoCliente)
                .map(cliente -> {
                    ClienteResponseDto clienteResponseDto = clienteResponseDtoFactory.getClienteResponseDto(
                            cliente.getClass().getSimpleName());
                    clienteResponseDto = modelMapper.map(cliente, clienteResponseDto.getClass());
                    return clienteResponseDto;
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public ClienteResponsePageDto pesquisarClientes(ClienteFilterDto filter, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clientesPage = clienteRepository.pesquisarPage(pageable);

        List<ClienteResponseDto> clientes = clientesPage.stream().map(cliente -> {
            ClienteResponseDto clienteResponseDto = clienteResponseDtoFactory.getClienteResponseDto(
                    cliente.getClass().getSimpleName());
            clienteResponseDto = modelMapper.map(cliente, clienteResponseDto.getClass());
            return clienteResponseDto;
        }).collect(Collectors.toList());

        ClienteResponsePageDto clienteResponsePageDto = new ClienteResponsePageDto();
        clienteResponsePageDto.setContent(clientes);
        clienteResponsePageDto.setPage(page);
        clienteResponsePageDto.setSize(size);
        clienteResponsePageDto.setTotal(clientesPage.getTotalElements());
        clienteResponsePageDto.setTotalPages(clientesPage.getTotalPages());

        return clienteResponsePageDto;

    }

}
