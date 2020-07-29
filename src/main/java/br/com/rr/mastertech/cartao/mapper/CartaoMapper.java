package br.com.rr.mastertech.cartao.mapper;

import br.com.rr.mastertech.cartao.client.dto.ClienteDTO;
import br.com.rr.mastertech.cartao.domain.Cartao;
import br.com.rr.mastertech.cartao.dto.response.CartaoDTO;
import org.springframework.stereotype.Component;

@Component
public class CartaoMapper {

    public CartaoDTO toCartaoDTO(Cartao entity) {
        CartaoDTO dto = new CartaoDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setClienteId(entity.getClienteId());
        dto.setAtivo(entity.getAtivo());

        return dto;
    }

    public Cartao toCartao(String numero, ClienteDTO cliente, Boolean ativo) {
        Cartao cartao = new Cartao();
        cartao.setNumero(numero);
        cartao.setClienteId(cliente.getId());
        cartao.setAtivo(ativo);

        return cartao;
    }
}
