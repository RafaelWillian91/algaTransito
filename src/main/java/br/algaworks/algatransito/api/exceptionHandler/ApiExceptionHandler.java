package br.algaworks.algatransito.api.exceptionHandler;

import br.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import br.algaworks.algatransito.domain.exception.NegocioException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://camposInvalidos.com/erros/campos-invalidos"));

        Map<String, String> fields = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                        DefaultMessageSourceResolvable::getDefaultMessage));

        problemDetail.setProperty("invalidFields", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ProblemDetail handleNegocio(NegocioException e){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ProblemDetail handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://camposInvalidos.com/erros/nao-encontrado"));
        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation (DataIntegrityViolationException e){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso está em uso");
        problemDetail.setType(URI.create("https://camposInvalidos.com/erros/recurso-em-uso"));
        return problemDetail;
    }


}
