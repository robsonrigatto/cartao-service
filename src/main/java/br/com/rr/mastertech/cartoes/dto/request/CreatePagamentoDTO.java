package br.com.rr.mastertech.cartoes.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatePagamentoDTO {

    @JsonProperty("cartao_id")
    private Integer idCartao;

    private String descricao;

    private Double valor;
}
