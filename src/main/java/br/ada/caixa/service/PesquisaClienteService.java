package br.ada.caixa.service;

import br.ada.caixa.dto.filter.ClienteFilterDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.dto.response.ClienteResponsePageDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.factory.ClienteResponseDtoFactory;
import br.ada.caixa.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PesquisaClienteService {

    final private ClienteRepository clienteRepository;
    final private ModelMapper modelMapper;
    final private ClienteResponseDtoFactory clienteResponseDtoFactory;

    public PesquisaClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper,
                          ClienteResponseDtoFactory clienteResponseDtoFactory) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.clienteResponseDtoFactory = clienteResponseDtoFactory;
    }

    public ClienteResponseDto pesquisarCliente(String documentoCliente) {
        return clienteRepository.findById(documentoCliente)
                .map(cliente -> {
                    ClienteResponseDto clienteResponseDto = clienteResponseDtoFactory.getClienteResponseDto(
                            cliente.getTipoCliente());
                    clienteResponseDto = modelMapper.map(cliente, clienteResponseDto.getClass());
                    return clienteResponseDto;
                })
                .orElseThrow(() -> new ValidacaoException("Cliente não encontrado"));
    }

    public ClienteResponsePageDto pesquisarClientes(ClienteFilterDto filter, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clientesPage = clienteRepository.pesquisarPage(
                filter.getTipoCliente() != null ? filter.getTipoCliente().toUpperCase() : null,
                filter.getNome(),
                pageable);

        List<ClienteResponseDto> clientes = clientesPage.stream().map(cliente -> {
            ClienteResponseDto clienteResponseDto = clienteResponseDtoFactory.getClienteResponseDto(
                    cliente.getTipoCliente());
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
