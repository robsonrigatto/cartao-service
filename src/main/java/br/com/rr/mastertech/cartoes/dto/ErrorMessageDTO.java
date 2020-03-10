package br.com.rr.mastertech.cartoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {

    private Integer statusCode;
    private String message;
}
