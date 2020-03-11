package br.com.rr.mastertech.cliente.mapper;

import br.com.rr.mastertech.cliente.domain.Cliente;
import br.com.rr.mastertech.cliente.dto.response.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente entity) {
        return new ClienteDTO(entity.getId(), entity.getName());
    }
}
