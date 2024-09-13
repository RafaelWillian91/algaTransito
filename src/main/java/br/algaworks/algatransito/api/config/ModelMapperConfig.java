package br.algaworks.algatransito.api.config;

import br.algaworks.algatransito.api.model.modelOutputVeiculo.VeiculoRepresentationModel;
import br.algaworks.algatransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Veiculo.class, VeiculoRepresentationModel.class)
               .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoRepresentationModel::setNumeroPlaca));

        return modelMapper;
    };





}
