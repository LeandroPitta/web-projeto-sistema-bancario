package br.ada.caixa.service.conta;

import br.ada.caixa.dto.filter.ContaFilterDto;
import br.ada.caixa.dto.response.*;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PesquisaContaService {

    final private ContaRepository contaRepository;
    final private ModelMapper modelMapper;

    public PesquisaContaService(ContaRepository contaRepository,
                                ModelMapper modelMapper) {
        this.contaRepository = contaRepository;
        this.modelMapper = modelMapper;
    }

    public ContaEClienteResponseDto pesquisarConta(Long numeroConta) {
        return contaRepository.findById(numeroConta)
                .map(conta -> modelMapper.map(conta, ContaEClienteResponseDto.class))
                .orElseThrow(() -> new ValidacaoException("Conta não encontrada"));
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
