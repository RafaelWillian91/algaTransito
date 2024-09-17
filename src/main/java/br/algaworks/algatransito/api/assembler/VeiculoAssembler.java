package br.algaworks.algatransito.api.assembler;

import br.algaworks.algatransito.api.model.modelOutputVeiculoDTO.VeiculoRepresentationModelDTO;
import br.algaworks.algatransito.api.model.modelInputVeiculo.VeiculoInputModel;
import br.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoAssembler {

    private ModelMapper modelMapper;

    public Veiculo veiculoInputModel(VeiculoInputModel veiculo){
        return modelMapper.map(veiculo, Veiculo.class);
    }
    public final VeiculoRepresentationModelDTO toModel(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoRepresentationModelDTO.class);
    }

    public List<VeiculoRepresentationModelDTO> convertListDTO(List<Veiculo> listVeiculo){
        return  listVeiculo.stream()
                .map(this::toModel)
                .toList();
    }

}
