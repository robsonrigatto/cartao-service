package br.com.rr.mastertech.cartoes.mapper;

import br.com.rr.mastertech.cartoes.domain.Pagamento;
import br.com.rr.mastertech.cartoes.dto.response.PagamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public PagamentoDTO toDTO(Pagamento entity) {
        return PagamentoDTO.builder()
                .id(entity.getId()).descricao(entity.getDescricao())
                .idCartao(entity.getCartao().getId()).valor(entity.getValor())
                .build();
    }
}
