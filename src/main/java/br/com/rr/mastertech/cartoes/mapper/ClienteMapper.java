package br.com.rr.mastertech.cartoes.mapper;

import br.com.rr.mastertech.cartoes.domain.Cliente;
import br.com.rr.mastertech.cartoes.dto.response.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente entity) {
        return new ClienteDTO(entity.getId(), entity.getName());
    }
}
