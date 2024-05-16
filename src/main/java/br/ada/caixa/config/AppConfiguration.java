package br.ada.caixa.config;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.request.ContaRequestDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.factory.ContaFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(ClientePFRequestDto.class, ClientePF.class)
                .addMapping(ClientePFRequestDto::getCpf, ClientePF::setDocumentoCliente);

        modelMapper.typeMap(ClientePJRequestDto.class, ClientePJ.class)
                .addMapping(ClientePJRequestDto::getCnpj, ClientePJ::setDocumentoCliente);

        TypeMap<ContaRequestDto, Conta> typeMap = modelMapper.createTypeMap(ContaRequestDto.class, Conta.class);
        typeMap.addMappings(mapper -> mapper.map(ContaRequestDto::getDocumentoCliente,
                (dest, v) -> dest.getCliente().setDocumentoCliente((String) v)));

        return modelMapper;
    }

    @Bean
    public ContaFactory contaFactory() {
        return new ContaFactory();
    }

}
