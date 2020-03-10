package br.com.rr.mastertech.cartoes.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagamentoDTO {

    private Integer id;

    @JsonProperty("cartao_id")
    private Integer idCartao;

    private String descricao;

    private Double valor;
}
