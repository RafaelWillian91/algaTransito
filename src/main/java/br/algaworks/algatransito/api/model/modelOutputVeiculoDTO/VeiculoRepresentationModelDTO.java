package br.algaworks.algatransito.api.model.modelOutputVeiculoDTO;

import br.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;


import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoRepresentationModelDTO {


    private Long id;
    private String marca;
    private ProprietarioRepresentationModelDTO proprietario;
    private String modelo;
    private String numeroPlaca;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;



}
