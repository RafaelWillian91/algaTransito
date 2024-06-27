package br.algaworks.algatransito.api.controller;

import br.algaworks.algatransito.domain.exception.NegocioException;
import br.algaworks.algatransito.domain.model.Proprietario;
import br.algaworks.algatransito.domain.repository.ProprietarioRepository;
import br.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final RegistroProprietarioService registroProprietarioService;
    private final ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar(){
        //return proprietarioRepository.findByNome("Rafael Willian");
       return proprietarioRepository.findAll();
       // return proprietarioRepository.findByNomeContaining("o");
    }

    @GetMapping("/{prorietarioId}")
    public ResponseEntity<Proprietario> buscarProprietario(@PathVariable Long prorietarioId){

        return proprietarioRepository.findById(prorietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//retorno o 201 caso o recurso seja criado com sucesso.
    public Proprietario adicionaProprietario(@RequestBody @Valid Proprietario proprietario){
        return registroProprietarioService.salvar(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,@Valid @RequestBody Proprietario proprietario){

        if(!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);
        registroProprietarioService.salvar(proprietario);
        return ResponseEntity.ok().body(proprietario);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId){

        if(!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.deletar(proprietarioId);
        return ResponseEntity.noContent().build();

    }


}














