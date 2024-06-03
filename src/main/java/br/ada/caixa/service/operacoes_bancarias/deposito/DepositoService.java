package br.ada.caixa.service.operacoes_bancarias.deposito;

import br.ada.caixa.dto.request.SaqueOuDepositoRequestDto;
import br.ada.caixa.dto.response.DepositoResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.repository.ContaRepository;
import br.ada.caixa.service.util.VerificacaoTitularContaCliente;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepositoService {

    private final ContaRepository contaRepository;
    private final DepositoRegrasNegocio depositoRegrasNegocio;
    private final VerificacaoTitularContaCliente verificacaoTitularContaCliente;

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
