package br.com.rr.mastertech.cliente.controller;

import br.com.rr.mastertech.cliente.domain.Cliente;
import br.com.rr.mastertech.cliente.dto.request.CreateClienteDTO;
import br.com.rr.mastertech.cliente.dto.response.ClienteDTO;
import br.com.rr.mastertech.cliente.mapper.ClienteMapper;
import br.com.rr.mastertech.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente entity = clienteService.findById(id);
        return ResponseEntity.ok(clienteMapper.toDTO(entity));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody CreateClienteDTO createDTO) {
        Cliente entity = this.clienteService.create(createDTO.getName());
        return new ResponseEntity(clienteMapper.toDTO(entity), HttpStatus.CREATED);
    }
}
