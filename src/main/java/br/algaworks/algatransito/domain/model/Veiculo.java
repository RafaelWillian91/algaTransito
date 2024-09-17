package br.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Proprietario proprietario;

    @OneToMany(mappedBy = "veiculo")
    private List<Autuacao> autuacoes = new ArrayList<>();

    private String modelo;

    private String placa;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;


    private OffsetDateTime dataCadastro;


    private OffsetDateTime dataApreensao;

    public Autuacao adicionarAutuacao (Autuacao autuacao){
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }
}
