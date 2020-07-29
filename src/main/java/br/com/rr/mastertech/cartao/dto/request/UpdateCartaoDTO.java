package br.com.rr.mastertech.cartao.dto.request;

import javax.validation.constraints.NotNull;

public class UpdateCartaoDTO {

    @NotNull
    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
