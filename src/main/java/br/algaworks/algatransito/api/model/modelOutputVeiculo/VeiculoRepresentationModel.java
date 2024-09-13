package br.algaworks.algatransito.api.model.modelOutputVeiculo;

import br.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;


import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoRepresentationModel {


    private Long id;
    private String marca;
    private ProprietarioRepresentationResumoModel proprietario;
    private String modelo;
    private String numeroPlaca;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;



}
