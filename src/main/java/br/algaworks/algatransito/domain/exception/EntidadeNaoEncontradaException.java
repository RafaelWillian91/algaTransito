package br.algaworks.algatransito.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException{
    public EntidadeNaoEncontradaException(String exception) {
        super(exception);
    }
}
