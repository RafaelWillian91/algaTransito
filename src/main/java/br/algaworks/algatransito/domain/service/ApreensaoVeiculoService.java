package br.algaworks.algatransito.domain.service;

import br.algaworks.algatransito.domain.model.StatusVeiculo;
import br.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {

    private CadastroVeiculoService cadastroVeiculoService;

    @Transactional
    public void apreender(Long idVeiculo){
        Veiculo veiculo = cadastroVeiculoService.buscar(idVeiculo);
        veiculo.apreender();
    }

    @Transactional
    public void removerApreensao(Long idVeiculo){
        Veiculo veiculo = cadastroVeiculoService.buscar(idVeiculo);
        veiculo.removerApreensao();
    }

}
