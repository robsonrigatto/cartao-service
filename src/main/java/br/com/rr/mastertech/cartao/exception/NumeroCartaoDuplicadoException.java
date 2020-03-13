package br.com.rr.mastertech.cartao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "número de cartão já existe")
public class NumeroCartaoDuplicadoException extends RuntimeException {
}
