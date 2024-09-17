package br.algaworks.algatransito.domain.service;

import br.algaworks.algatransito.domain.model.Autuacao;
import br.algaworks.algatransito.domain.model.Veiculo;
import br.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ServicoAutuacaoService {

    private  CadastroVeiculoService cadastroVeiculoService;
    @Transactional
    public Autuacao autuacao (Long idVeiculo,  Autuacao novaAutuacao){

        Veiculo veiculo = cadastroVeiculoService.buscar(idVeiculo);

        return veiculo.adicionarAutuacao(novaAutuacao);
    }


}
