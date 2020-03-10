package br.com.rr.mastertech.cartoes.controller;

import br.com.rr.mastertech.cartoes.domain.Cliente;
import br.com.rr.mastertech.cartoes.dto.request.CreateClienteDTO;
import br.com.rr.mastertech.cartoes.dto.response.ClienteDTO;
import br.com.rr.mastertech.cartoes.mapper.ClienteMapper;
import br.com.rr.mastertech.cartoes.service.ClienteService;
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
