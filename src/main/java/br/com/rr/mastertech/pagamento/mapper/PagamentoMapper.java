package br.com.rr.mastertech.pagamento.mapper;

import br.com.rr.mastertech.pagamento.domain.Pagamento;
import br.com.rr.mastertech.pagamento.dto.response.PagamentoDTO;
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
