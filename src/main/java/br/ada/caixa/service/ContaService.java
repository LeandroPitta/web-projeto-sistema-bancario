package br.ada.caixa.service;

import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.factory.ContaFactory;
import br.ada.caixa.repository.ClienteRepository;
import br.ada.caixa.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ContaService {

    final private ContaRepository contaRepository;
    final private ClienteRepository clienteRepository;
    final private ModelMapper modelMapper;
    final private ContaFactory contaFactory;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository,
                        ModelMapper modelMapper, ContaFactory contaFactory) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.contaFactory = contaFactory;

        TypeMap<ContaRequestDto, Conta> typeMap = modelMapper.createTypeMap(ContaRequestDto.class, Conta.class);
        typeMap.addMappings(mapper -> mapper.map(ContaRequestDto::getIdCliente,
                (dest, v) -> dest.getCliente().setDocumento((String) v)));
    }

    public void abrirConta(ContaRequestDto contaRequestDto) {
        Conta instanciaConta = contaFactory.getConta(contaRequestDto.getTipoConta());
        Conta conta = modelMapper.map(contaRequestDto, instanciaConta.getClass());
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setDataAbertura(LocalDate.now());

        Cliente cliente = clienteRepository.findById(contaRequestDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        conta.setCliente(cliente);
        contaRepository.save(conta);
    }
}
