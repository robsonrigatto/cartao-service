package br.com.rr.mastertech.cartoes.dto.request;

import lombok.Data;

@Data
public class CreateCartaoDTO {

    private String numero;
    private Integer clienteId;
}
