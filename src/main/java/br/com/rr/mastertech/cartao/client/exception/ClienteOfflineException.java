package br.com.rr.mastertech.cartao.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "serviço de cliente indisponível")
public class ClienteOfflineException extends RuntimeException {
}
