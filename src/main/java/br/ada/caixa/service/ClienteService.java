package br.ada.caixa.service;

import br.ada.caixa.dto.filter.ClienteFilterDto;
import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.dto.response.ClienteResponsePageDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.enums.Status;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.factory.ClienteResponseDtoFactory;
import br.ada.caixa.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    final private ClienteRepository clienteRepository;
    final private ModelMapper modelMapper;
    final private ClienteResponseDtoFactory clienteResponseDtoFactory;
    final private ContaService contaService;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper,
                          ClienteResponseDtoFactory clienteResponseDtoFactory, ContaService contaService) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.clienteResponseDtoFactory = clienteResponseDtoFactory;
        this.contaService = contaService;
    }

    public void cadastrarClientePF(ClientePFRequestDto clientePFRequestDto) {
        clienteRepository.findById(clientePFRequestDto.getCpf()).ifPresent(cliente -> {
            throw new ValidacaoException("Cliente já possui cadastro");
        });

        ClientePF cliente = modelMapper.map(clientePFRequestDto, ClientePF.class);

        cliente.setTipoCliente("PF");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(Status.ATIVO);
        clienteRepository.save(cliente);

        ContaRequestDto conta = new ContaRequestDto();
        conta.setDocumentoCliente(cliente.getDocumentoCliente());
        conta.setTipoConta("CORRENTE");
        contaService.abrirConta(conta);

    }

    public void cadastrarClientePJ(ClientePJRequestDto clientePJRequestDto) {
        clienteRepository.findById(clientePJRequestDto.getCnpj()).ifPresent(cliente -> {
            throw new ValidacaoException("Cliente já possui cadastro");
        });

        ClientePJ cliente = modelMapper.map(clientePJRequestDto, ClientePJ.class);

        cliente.setTipoCliente("PJ");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(Status.ATIVO);

        clienteRepository.save(cliente);

        ContaRequestDto conta = new ContaRequestDto();
        conta.setDocumentoCliente(cliente.getDocumentoCliente());
        conta.setTipoConta("CORRENTE");
        contaService.abrirConta(conta);
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
                filter.getDocumentoCliente(),
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
