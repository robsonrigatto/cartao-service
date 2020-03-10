package br.com.rr.mastertech.cartoes.handler;

import br.com.rr.mastertech.cartoes.dto.ErrorMessageDTO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ErrorMessageDTO errorDTO = new ErrorMessageDTO(404, "Registro não encontrado");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientError(HttpClientErrorException ex) {
        ErrorMessageDTO errorDTO = new ErrorMessageDTO(ex.getRawStatusCode(), ex.getStatusText());
        return new ResponseEntity<>(errorDTO, HttpStatus.valueOf(ex.getRawStatusCode()));
    }

}
