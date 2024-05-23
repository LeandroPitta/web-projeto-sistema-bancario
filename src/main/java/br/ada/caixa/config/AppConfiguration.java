package br.ada.caixa.config;

import br.ada.caixa.dto.request.ClientePFRequestDto;
import br.ada.caixa.dto.request.ClientePJRequestDto;
import br.ada.caixa.dto.response.*;
import br.ada.caixa.entity.*;
import br.ada.caixa.factory.ClienteResponseDtoFactory;
import br.ada.caixa.factory.ContaFactory;
import br.ada.caixa.service.regras_negocio.AberturaContaRegrasNegocio;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> dateConverter = new AbstractConverter<>() {
            protected LocalDate convert(String source) {
                return source == null ? null : LocalDate.parse(source);
            }
        };
        Converter<LocalDate, String> dateStringConverter = new AbstractConverter<>() {
            protected String convert(LocalDate source) {
                return source == null ? null : source.toString(); //yyyy-MM-dd
            }
        };
        modelMapper.addConverter(dateConverter);
        modelMapper.addConverter(dateStringConverter);

        modelMapper.typeMap(ClientePFRequestDto.class, ClientePF.class)
                .addMapping(ClientePFRequestDto::getCpf, ClientePF::setDocumentoCliente);

        modelMapper.typeMap(ClientePJRequestDto.class, ClientePJ.class)
                .addMapping(ClientePJRequestDto::getCnpj, ClientePJ::setDocumentoCliente);

        modelMapper.typeMap(ClientePF.class, ClientePFResponseDto.class)
                .addMapping(ClientePF::getDocumentoCliente, ClientePFResponseDto::setCpf);

        modelMapper.typeMap(ClientePJ.class, ClientePJResponseDto.class)
                .addMapping(ClientePJ::getDocumentoCliente, ClientePJResponseDto::setCnpj);

        Converter<Cliente, ClienteSimplificadoResponseDto> toClienteSimplificadoResponseDto = new Converter<Cliente, ClienteSimplificadoResponseDto>() {
            public ClienteSimplificadoResponseDto convert(MappingContext<Cliente, ClienteSimplificadoResponseDto> context) {
                Cliente source = context.getSource();
                ClienteSimplificadoResponseDto destination = new ClienteSimplificadoResponseDto();

                if (source instanceof ClientePF) {
                    destination.setNome(((ClientePF) source).getNome());
                } else if (source instanceof ClientePJ) {
                    destination.setNome(((ClientePJ) source).getRazaoSocial());
                }

                destination.setDocumentoCliente(source.getDocumentoCliente());
                destination.setDataCadastro(source.getDataCadastro());
                destination.setTipoCliente(source.getTipoCliente());
                destination.setStatus(source.getStatus());

                return destination;
            }
        };

        modelMapper.addConverter(toClienteSimplificadoResponseDto);

        return modelMapper;

    }

    @Bean
    public ContaFactory contaFactory() {
        return new ContaFactory();
    }

    @Bean
    public ClienteResponseDtoFactory clienteResponseDtoFactory() {
        return new ClienteResponseDtoFactory();
    }

    @Bean
    public AberturaContaRegrasNegocio aberturaContaRegrasNegocio() {
        return new AberturaContaRegrasNegocio();
    }
}
