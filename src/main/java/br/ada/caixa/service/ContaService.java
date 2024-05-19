package br.ada.caixa.service;

import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.ContaEClienteResponseDto;
import br.ada.caixa.dto.response.ContaResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.factory.ContaFactory;
import br.ada.caixa.repository.ClienteRepository;
import br.ada.caixa.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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
    }

    public void abrirConta(ContaRequestDto contaRequestDto) {

        Cliente cliente = clienteRepository.findById(contaRequestDto.getDocumentoCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Conta instanciaConta = contaFactory.getConta(contaRequestDto.getTipoConta());
        Conta conta = modelMapper.map(contaRequestDto, instanciaConta.getClass());
        conta.setCliente(cliente);
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setDataAbertura(LocalDate.now());

        contaRepository.save(conta);
    }

    public ContaEClienteResponseDto pesquisarConta(Long numeroConta) {
        return contaRepository.findById(numeroConta)
                .map(conta -> modelMapper.map(conta, ContaEClienteResponseDto.class))
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

}
