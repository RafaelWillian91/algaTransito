package br.algaworks.algatransito.api.model.modelOutputVeiculoDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AutuacaoModelDTO {


        private Long id;
        private String description;
        private BigDecimal valorMulta;

        private OffsetDateTime dataOcorrencia;

}
