package br.com.rr.mastertech.cartao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {

    private Integer statusCode;
    private String message;
}
