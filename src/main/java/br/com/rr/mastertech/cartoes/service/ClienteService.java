package br.com.rr.mastertech.cartoes.service;

import br.com.rr.mastertech.cartoes.domain.Cliente;
import br.com.rr.mastertech.cartoes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if(!optionalCliente.isPresent()) {
            throw new EntityNotFoundException();
        }

        return optionalCliente.get();
    }

    public Cliente create(String name) {
        return this.clienteRepository.save(Cliente.builder().name(name).build());
    }
}
