package br.ada.caixa.service;

import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.InvestirRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransfereRequestDto;
import br.ada.caixa.dto.response.SaldoResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OperacoesBancariasService {

    final private ModelMapper modelMapper;

    public OperacoesBancariasService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void depositar(DepositoRequestDto depositoRequestDto) {

        System.out.println(depositoRequestDto.toString());

    }

    public void sacar(SaqueRequestDto saqueRequestDto) {

        System.out.println(saqueRequestDto.toString());

    }

    public void transferir(TransfereRequestDto transfereRequestDto) {

        System.out.println(transfereRequestDto.toString());

    }

    public SaldoResponseDto consultarSaldo(String idConta) {

        SaldoResponseDto saldo = new SaldoResponseDto();
        saldo.setIdConta(idConta);
        saldo.setSaldo(BigDecimal.valueOf(1000));
        return saldo;

    }

    public void investir(InvestirRequestDto investirRequestDto) {

        System.out.println(investirRequestDto.toString());

    }
}
