package br.ada.caixa.service.cliente;

import br.ada.caixa.dto.request.ClienteRequestDto;
import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.enums.Status;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ClienteRepository;
import br.ada.caixa.service.conta.AberturaContaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CadastroClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;
    private final AberturaContaService aberturaContaService;

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
