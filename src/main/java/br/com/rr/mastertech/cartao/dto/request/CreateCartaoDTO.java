package br.com.rr.mastertech.cartao.dto.request;

import com.sun.istack.NotNull;

public class CreateCartaoDTO {

    @NotNull
    private String numero;

    @NotNull
    private Integer clienteId;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}
