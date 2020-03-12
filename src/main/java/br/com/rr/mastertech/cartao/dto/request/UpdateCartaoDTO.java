package br.com.rr.mastertech.cartao.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCartaoDTO {

    @NotNull
    private Boolean ativo;
}
