package br.com.rr.mastertech.cartao.controller;

import br.com.rr.mastertech.cartao.domain.Cartao;
import br.com.rr.mastertech.cartao.dto.request.CreateCartaoDTO;
import br.com.rr.mastertech.cartao.dto.request.UpdateCartaoDTO;
import br.com.rr.mastertech.cartao.dto.response.CartaoDTO;
import br.com.rr.mastertech.cartao.mapper.CartaoMapper;
import br.com.rr.mastertech.cartao.service.CartaoService;
import br.com.rr.mastertech.cliente.domain.Cliente;
import br.com.rr.mastertech.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private CartaoMapper cartaoMapper;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<CartaoDTO> create(@RequestBody CreateCartaoDTO createDTO) {
        try {
            Cliente cliente = clienteService.findById(createDTO.getClienteId());
            Cartao entity = cartaoService.create(createDTO.getNumero(), cliente);
            return new ResponseEntity(cartaoMapper.toDTO(entity), HttpStatus.CREATED);

        } catch (EntityNotFoundException ex) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "é obrigatório informar um cliente existente");
        }
    }

    @PatchMapping("/{numero}")
    public ResponseEntity<CartaoDTO> update(@PathVariable String numero, @RequestBody UpdateCartaoDTO updateDTO) {
        Cartao entity = cartaoService.update(numero, updateDTO.getAtivo());
        return ResponseEntity.ok(cartaoMapper.toDTO(entity));
    }

    @GetMapping("/{numero}")
    public ResponseEntity<CartaoDTO> findByNumero(@PathVariable String numero) {
        Cartao entity = cartaoService.findByNumero(numero);
        return ResponseEntity.ok(cartaoMapper.toDTO(entity));
    }
}
