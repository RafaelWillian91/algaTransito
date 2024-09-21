package br.algaworks.algatransito.domain.service;

import br.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import br.algaworks.algatransito.domain.exception.NegocioException;
import br.algaworks.algatransito.domain.model.Proprietario;
import br.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistroProprietarioService {

    private ProprietarioRepository proprietarioRepository;

    public Proprietario buscar(Long proprietarioId){
        return proprietarioRepository.findById(proprietarioId)
                .orElseThrow(() -> new NegocioException("Proprietário não encontrado"));
    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario){

        //Verifica se já existe um e-mail cadastrado.
        boolean emailEncontrado = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario)).isPresent();

        if(emailEncontrado){
            throw new NegocioException("Já existe um usuário cadastrado com esse e-mail!");
        }

        return proprietarioRepository.save(proprietario);

    }

    @Transactional
    public void deletar(Long proprietarioId){
        proprietarioRepository.deleteById(proprietarioId);
    }

}
