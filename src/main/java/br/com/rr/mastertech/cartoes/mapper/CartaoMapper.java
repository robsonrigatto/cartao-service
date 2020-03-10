package br.com.rr.mastertech.cartoes.mapper;

import br.com.rr.mastertech.cartoes.domain.Cartao;
import br.com.rr.mastertech.cartoes.dto.response.CartaoDTO;
import org.springframework.stereotype.Component;

@Component
public class CartaoMapper {

    public CartaoDTO toDTO(Cartao entity) {
        return CartaoDTO.builder().id(entity.getId())
                .numero(entity.getNumero())
                .clienteId(entity.getCliente().getId())
                .ativo(entity.getAtivo()).build();
    }
}
