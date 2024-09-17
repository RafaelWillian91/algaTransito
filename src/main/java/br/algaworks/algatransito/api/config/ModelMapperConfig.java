package br.algaworks.algatransito.api.config;

import br.algaworks.algatransito.api.model.modelOutputVeiculoDTO.VeiculoRepresentationModelDTO;
import br.algaworks.algatransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Veiculo.class, VeiculoRepresentationModelDTO.class)
               .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoRepresentationModelDTO::setNumeroPlaca));

        return modelMapper;
    };





}
