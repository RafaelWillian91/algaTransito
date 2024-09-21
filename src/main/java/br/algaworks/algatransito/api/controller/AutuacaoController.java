package br.algaworks.algatransito.api.controller;


import br.algaworks.algatransito.api.assembler.AutuacaoAssembler;
import br.algaworks.algatransito.api.model.modelInputVeiculo.AutuacaoInput;
import br.algaworks.algatransito.api.model.modelOutputVeiculoDTO.AutuacaoModelDTO;
import br.algaworks.algatransito.domain.model.Autuacao;
import br.algaworks.algatransito.domain.model.Veiculo;
import br.algaworks.algatransito.domain.repository.VeiculoRepository;
import br.algaworks.algatransito.domain.service.CadastroVeiculoService;
import br.algaworks.algatransito.domain.service.ServicoAutuacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final ServicoAutuacaoService servicoAutuacaoService;
    private final AutuacaoAssembler autuacaoAssembler;

    private final CadastroVeiculoService cadastroVeiculoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModelDTO registrar(@PathVariable Long veiculoId,
                                      @Valid @RequestBody AutuacaoInput autuacaoInput){

            Autuacao autuacao = autuacaoAssembler.autuacaoToEntity(autuacaoInput);
            servicoAutuacaoService.registrar(veiculoId, autuacao);
            return autuacaoAssembler.toModel(autuacao);
    }

    @GetMapping
    public List<AutuacaoModelDTO> listar (@PathVariable Long veiculoId){
        Veiculo veiculo = cadastroVeiculoService.buscar(veiculoId);
        return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
    }



}
