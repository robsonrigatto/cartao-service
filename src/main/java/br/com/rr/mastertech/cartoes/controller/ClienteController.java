package br.com.rr.mastertech.cartoes.controller;

import br.com.rr.mastertech.cartoes.domain.Cliente;
import br.com.rr.mastertech.cartoes.dto.request.CreateClienteDTO;
import br.com.rr.mastertech.cartoes.dto.response.ClienteDTO;
import br.com.rr.mastertech.cartoes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if(!optionalCliente.isPresent()) {
            throw new EntityNotFoundException();
        }

        Cliente entity = optionalCliente.get();
        ClienteDTO dto = new ClienteDTO(entity.getId(), entity.getName());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody CreateClienteDTO createDTO) {
        Cliente entity = new Cliente();
        entity.setName(createDTO.getName());
        entity = this.clienteRepository.save(entity);

        ClienteDTO dto = new ClienteDTO(entity.getId(), entity.getName());
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }
}
