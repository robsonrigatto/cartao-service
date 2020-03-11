package br.com.rr.mastertech.cartao.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartaoDTO {

    private Integer id;
    private String numero;
    private Integer clienteId;
    private Boolean ativo;
}
