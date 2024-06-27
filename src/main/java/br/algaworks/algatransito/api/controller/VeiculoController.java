package br.algaworks.algatransito.api.controller;

import br.algaworks.algatransito.domain.exception.NegocioException;
import br.algaworks.algatransito.domain.model.Veiculo;
import br.algaworks.algatransito.domain.repository.VeiculoRepository;
import br.algaworks.algatransito.domain.service.CadastroVeiculoService;
import br.algaworks.algatransito.domain.service.RegistroProprietarioService;
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
    @GetMapping
    public List<Veiculo> listaVeiculos(){
        return veiculoRepository.findAll();
    }

    @GetMapping("/{idVeiculo}")
    public ResponseEntity<Veiculo> buscar (@PathVariable Long idVeiculo){
       return veiculoRepository.findById(idVeiculo)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrarVeiculo")
    @ResponseStatus(HttpStatus.CREATED)//Retorno do HHTP Status mais apropriado para criacao
    public ResponseEntity<Veiculo> cadastrarVeiculo(@Valid @RequestBody Veiculo veiculo){

        return ResponseEntity.ok(cadastroVeiculoService.cadastrarVeiculo(veiculo));

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
