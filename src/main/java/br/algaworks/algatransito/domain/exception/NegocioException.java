package br.algaworks.algatransito.domain.exception;

public class NegocioException extends RuntimeException{

    public NegocioException (String exception){
        super(exception);
    }

}
