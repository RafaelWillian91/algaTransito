package br.algaworks.algatransito.api.assembler;

import br.algaworks.algatransito.api.model.modelInputVeiculo.AutuacaoInput;
import br.algaworks.algatransito.api.model.modelOutputVeiculoDTO.AutuacaoModelDTO;
import br.algaworks.algatransito.domain.model.Autuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AutuacaoAssembler {
    private final ModelMapper modelMapper;
    public AutuacaoModelDTO toModel(Autuacao autuacao){
        return modelMapper.map(autuacao, AutuacaoModelDTO.class);
    }

    public Autuacao autuacaoToEntity(AutuacaoInput autuacaoInput){
        return modelMapper.map(autuacaoInput, Autuacao.class);
    }

    public List<AutuacaoModelDTO> toCollectionModel(List<Autuacao> autuacoes){
        return autuacoes.stream()
                .map(this::toModel)
                .toList();

    }

}
