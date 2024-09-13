package br.algaworks.algatransito.api.assembler;

import br.algaworks.algatransito.api.model.modelOutputVeiculo.VeiculoRepresentationModel;
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
    public final VeiculoRepresentationModel toModel(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoRepresentationModel.class);
    }

    public List<VeiculoRepresentationModel> convertListDTO(List<Veiculo> listVeiculo){
        return  listVeiculo.stream()
                .map(this::toModel)
                .toList();
    }

}
