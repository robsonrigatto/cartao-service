package br.com.rr.mastertech.cartao.mapper;

import br.com.rr.mastertech.cartao.domain.Cartao;
import br.com.rr.mastertech.cartao.dto.response.CartaoDTO;
import org.springframework.stereotype.Component;

@Component
public class CartaoMapper {

    public CartaoDTO toDTO(Cartao entity) {
        return CartaoDTO.builder().id(entity.getId())
                .numero(entity.getNumero())
                .clienteId(entity.getClienteId())
                .ativo(entity.getAtivo()).build();
    }
}
