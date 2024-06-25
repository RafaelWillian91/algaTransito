package br.algaworks.algatransito.domain.model;

import br.algaworks.algatransito.domain.model.Proprietario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Veiculo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;

    @ManyToOne
//    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;
    private String modelo;
    private String placa;

    @JsonProperty(access = READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    @JsonProperty(access = READ_ONLY)
    private LocalDateTime dataCadastro;

    @JsonProperty(access = READ_ONLY)
    private LocalDateTime dataApreensao;


}
