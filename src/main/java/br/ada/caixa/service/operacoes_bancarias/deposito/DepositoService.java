package br.ada.caixa.service.operacoes_bancarias.deposito;

import br.ada.caixa.dto.request.SaqueOuDepositoRequestDto;
import br.ada.caixa.dto.response.DepositoResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.repository.ContaRepository;
import br.ada.caixa.service.util.VerificacaoTitularContaCliente;
import org.springframework.stereotype.Service;

@Service
public class DepositoService {

    final private ContaRepository contaRepository;
    final private DepositoRegrasNegocio depositoRegrasNegocio;
    final private VerificacaoTitularContaCliente verificacaoTitularContaCliente;

    public DepositoService(ContaRepository contaRepository,
                           DepositoRegrasNegocio depositoRegrasNegocio,
                           VerificacaoTitularContaCliente verificacaoTitularContaCliente) {
        this.contaRepository = contaRepository;
        this.depositoRegrasNegocio = depositoRegrasNegocio;
        this.verificacaoTitularContaCliente = verificacaoTitularContaCliente;
    }

    public DepositoResponseDto depositar(SaqueOuDepositoRequestDto depositoRequestDto) {

        Conta conta = verificacaoTitularContaCliente.verificar(
                contaRepository, depositoRequestDto.getNumeroConta(), depositoRequestDto.getDocumentoCliente());

        DepositoResponseDto depositoResponseDto = depositoRegrasNegocio.validarRegras(conta, depositoRequestDto.getValor());

        conta.setSaldo(conta.getSaldo().add(depositoResponseDto.getValorTotalCredito()));
        contaRepository.save(conta);
        contaRepository.findById(depositoRequestDto.getNumeroConta()).ifPresent(contaAtualizada -> {
            depositoResponseDto.setNovoSaldo(contaAtualizada.getSaldo());
        });

        return depositoResponseDto;
    }

}
