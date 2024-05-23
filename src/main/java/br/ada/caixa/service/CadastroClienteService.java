package br.ada.caixa.service;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.request.ClienteRequestDto;
import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.enums.Status;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.factory.ClienteResponseDtoFactory;
import br.ada.caixa.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastroClienteService {

    final private ClienteRepository clienteRepository;
    final private ModelMapper modelMapper;
    final private PesquisaContaService pesquisaContaService;
    final private AberturaContaService aberturaContaService;

    public CadastroClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper,
                                  PesquisaContaService pesquisaContaService, AberturaContaService aberturaContaService) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.pesquisaContaService = pesquisaContaService;
        this.aberturaContaService = aberturaContaService;
    }

    public <T extends ClienteRequestDto> void cadastrarCliente(T clienteRequestDto, Class<? extends Cliente> clienteClass) {

        clienteRepository.findById(clienteRequestDto.getDocumentoCliente()).ifPresent(cliente -> {
            throw new ValidacaoException("Cliente já possui cadastro");
        });

        Cliente cliente = modelMapper.map(clienteRequestDto, clienteClass);

        cliente.setTipoCliente(clienteRequestDto.getTipoCliente());
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(Status.ATIVO);
        clienteRepository.save(cliente);

        ContaRequestDto conta = new ContaRequestDto();
        conta.setDocumentoCliente(cliente.getDocumentoCliente());
        conta.setTipoConta("CORRENTE");
        aberturaContaService.abrirConta(conta);
    }

}
