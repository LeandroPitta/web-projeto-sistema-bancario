package br.ada.caixa.service;

import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.enums.TipoConta;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.factory.ContaFactory;
import br.ada.caixa.repository.ClienteRepository;
import br.ada.caixa.repository.ContaRepository;
import br.ada.caixa.service.regras_negocio.AberturaContaRegrasNegocio;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AberturaContaService {

    final private ContaRepository contaRepository;
    final private ClienteRepository clienteRepository;
    final private ModelMapper modelMapper;
    final private ContaFactory contaFactory;
    final private AberturaContaRegrasNegocio aberturaContaRegrasNegocio;

    public AberturaContaService(ContaRepository contaRepository, ClienteRepository clienteRepository,
                        ModelMapper modelMapper, ContaFactory contaFactory,
                                AberturaContaRegrasNegocio aberturaContaRegrasNegocio) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
        this.contaFactory = contaFactory;
        this.aberturaContaRegrasNegocio = aberturaContaRegrasNegocio;
    }

    public void abrirConta(ContaRequestDto contaRequestDto) {

        Cliente cliente = clienteRepository.findById(contaRequestDto.getDocumentoCliente())
                .orElseThrow(() -> new ValidacaoException("Cliente não encontrado"));

        aberturaContaRegrasNegocio.validarRegras(cliente, contaRequestDto);

        Conta instanciaConta = contaFactory.getConta(TipoConta.valueOf(contaRequestDto.getTipoConta()));
        Conta conta = modelMapper.map(contaRequestDto, instanciaConta.getClass());
        conta.setCliente(cliente);
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setDataAbertura(LocalDate.now());

        contaRepository.save(conta);
    }
}
