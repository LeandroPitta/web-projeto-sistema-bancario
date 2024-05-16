package br.ada.caixa.config;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.factory.ContaFactory;
import org.modelmapper.ModelMapper;
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

        modelMapper.typeMap(ClientePF.class, ClientePFResponseDto.class)
                .addMapping(ClientePF::getDocumentoCliente, ClientePFResponseDto::setCpf);

        modelMapper.typeMap(ClientePJ.class, ClientePJResponseDto.class)
                .addMapping(ClientePJ::getDocumentoCliente, ClientePJResponseDto::setCnpj);

        return modelMapper;
    }

    @Bean
    public ContaFactory contaFactory() {
        return new ContaFactory();
    }

}
