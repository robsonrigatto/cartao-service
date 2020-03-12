package br.com.rr.mastertech.cartao.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CreateCartaoDTO {

    @NotNull
    private String numero;

    @NotNull
    private Integer clienteId;
}
