package br.algaworks.algatransito.api.model;

import br.algaworks.algatransito.domain.model.Proprietario;
import br.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;


import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoRepresentationModel {


    private Long id;
    private String marca;
    private String nomeProprietario;
    private String modelo;
    private String placa;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;



}
