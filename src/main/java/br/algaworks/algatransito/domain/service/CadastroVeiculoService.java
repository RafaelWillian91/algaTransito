package br.algaworks.algatransito.domain.service;

import br.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import br.algaworks.algatransito.domain.exception.NegocioException;
import br.algaworks.algatransito.domain.model.Proprietario;
import br.algaworks.algatransito.domain.model.StatusVeiculo;
import br.algaworks.algatransito.domain.model.Veiculo;
import br.algaworks.algatransito.domain.repository.ProprietarioRepository;
import br.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class CadastroVeiculoService {


    private VeiculoRepository veiculoRepository;
    private RegistroProprietarioService registroProprietarioService;

    public Veiculo buscar(Long idVeiculo){
        return veiculoRepository.findById(idVeiculo).orElseThrow( () -> new EntidadeNaoEncontradaException("Veículo " +
                "não encontrado!"));
    }

    @Transactional
    public Veiculo cadastrarVeiculo(Veiculo novoVeiculo){

        //Id sempre tem que estar null, senao é uma atualizacao de um veiculo e nao um cadastro novo
        if(novoVeiculo.getId() != null){
            throw new NegocioException("Veículo ao ser cadastrado nao deve possuir um código");
        }

         boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca()
                ).filter(v -> !v.equals(novoVeiculo.getPlaca())).isPresent();

         if(placaEmUso){
             throw new NegocioException("Essa placa já existe no banco de dados");
         }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);

        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(novoVeiculo);
    }


}
