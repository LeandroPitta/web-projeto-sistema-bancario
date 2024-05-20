package br.ada.caixa.service;

import br.ada.caixa.dto.filter.ContaFilterDto;
import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.dto.response.*;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.enums.TipoConta;
import br.ada.caixa.factory.ContaFactory;
import br.ada.caixa.repository.ClienteRepository;
import br.ada.caixa.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

        Conta instanciaConta = contaFactory.getConta(TipoConta.valueOf(contaRequestDto.getTipoConta()));
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

    public ContaResponsePageDto pesquisarContas(ContaFilterDto filter, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Conta> contasPage = contaRepository.pesquisarPage(
                filter.getTipoConta() != null ? filter.getTipoConta().toUpperCase() : null,
                filter.getTipoCliente() != null ? filter.getTipoCliente().toUpperCase() : null,
                pageable);

        List<ContaEClienteResponseDto> contas = contasPage.stream()
                .map(conta -> modelMapper.map(conta, ContaEClienteResponseDto.class))
                .collect(Collectors.toList());

        ContaResponsePageDto contaResponsePageDto = new ContaResponsePageDto();
        contaResponsePageDto.setContent(contas);
        contaResponsePageDto.setPage(page);
        contaResponsePageDto.setSize(size);
        contaResponsePageDto.setTotal(contasPage.getTotalElements());
        contaResponsePageDto.setTotalPages(contasPage.getTotalPages());

        return contaResponsePageDto;

    }

}
