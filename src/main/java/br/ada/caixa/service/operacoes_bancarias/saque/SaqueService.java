package br.ada.caixa.service.operacoes_bancarias.saque;

import br.ada.caixa.dto.request.SaqueOuDepositoRequestDto;
import br.ada.caixa.dto.response.SaqueResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.repository.ContaRepository;
import br.ada.caixa.service.util.VerificacaoTitularContaCliente;
import org.springframework.stereotype.Service;

@Service
public class SaqueService {

    final private ContaRepository contaRepository;
    final private SaqueRegrasNegocio saqueRegrasNegocio;
    final private VerificacaoTitularContaCliente verificacaoTitularContaCliente;

    public SaqueService(ContaRepository contaRepository,
                        SaqueRegrasNegocio saqueRegrasNegocio,
                        VerificacaoTitularContaCliente verificacaoTitularContaCliente) {
        this.contaRepository = contaRepository;
        this.saqueRegrasNegocio = saqueRegrasNegocio;
        this.verificacaoTitularContaCliente = verificacaoTitularContaCliente;
    }

    public SaqueResponseDto sacar(SaqueOuDepositoRequestDto saqueRequestDto) {

        Conta conta = verificacaoTitularContaCliente.verificar(
                contaRepository, saqueRequestDto.getNumeroConta(), saqueRequestDto.getDocumentoCliente());

        SaqueResponseDto saqueResponseDto = saqueRegrasNegocio.validarRegras(conta, saqueRequestDto.getValor());

        conta.setSaldo(conta.getSaldo().subtract(saqueResponseDto.getValorTotalDebito()));
        contaRepository.save(conta);
        contaRepository.findById(saqueRequestDto.getNumeroConta()).ifPresent(contaAtualizada -> {
            saqueResponseDto.setSaldoAtual(contaAtualizada.getSaldo());
        });

        return saqueResponseDto;
    }

}
