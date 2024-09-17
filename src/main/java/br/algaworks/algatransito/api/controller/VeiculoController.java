package br.algaworks.algatransito.api.controller;

import br.algaworks.algatransito.api.assembler.VeiculoAssembler;
import br.algaworks.algatransito.api.model.modelOutputVeiculoDTO.VeiculoRepresentationModelDTO;
import br.algaworks.algatransito.api.model.modelInputVeiculo.VeiculoInputModel;
import br.algaworks.algatransito.domain.model.Veiculo;
import br.algaworks.algatransito.domain.repository.VeiculoRepository;
import br.algaworks.algatransito.domain.service.CadastroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final CadastroVeiculoService cadastroVeiculoService;
    private final VeiculoAssembler veiculoAssembler;


    @GetMapping
    public List<VeiculoRepresentationModelDTO> listaVeiculos(){
        return veiculoAssembler.convertListDTO(veiculoRepository.findAll());
    }

    @GetMapping("/{idVeiculo}")
    public ResponseEntity<VeiculoRepresentationModelDTO> buscar (@PathVariable Long idVeiculo){
       return veiculoRepository.findById(idVeiculo)
               .map(veiculoAssembler::toModel)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrarVeiculo")
    @ResponseStatus(HttpStatus.CREATED)//Retorno do HHTP Status mais apropriado para criacao
    public VeiculoRepresentationModelDTO cadastrarVeiculo(@Valid @RequestBody VeiculoInputModel veiculoInputModel){

            Veiculo novoVeiculo = veiculoAssembler.veiculoInputModel(veiculoInputModel);

            Veiculo veiculoCadastrado = cadastroVeiculoService.cadastrarVeiculo(novoVeiculo);

            return veiculoAssembler.toModel(veiculoCadastrado);

    }



}
