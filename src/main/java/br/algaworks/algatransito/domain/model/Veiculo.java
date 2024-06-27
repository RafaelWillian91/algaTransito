package br.algaworks.algatransito.domain.model;

import br.algaworks.algatransito.domain.model.Proprietario;
import br.algaworks.algatransito.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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

    @NotBlank
    private String marca;

    @NotNull
    @ManyToOne
    @Valid
    @ConvertGroup(from = Default.class, to =  ValidationGroups.ProprietarioId.class)
    private Proprietario proprietario;

    @NotBlank
    private String modelo;


    @NotBlank
    //XXX0000
    //XXX0X00
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @JsonProperty(access = READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    @JsonProperty(access = READ_ONLY)
    private LocalDateTime dataCadastro;

    @JsonProperty(access = READ_ONLY)
    private LocalDateTime dataApreensao;


}
