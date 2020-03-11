package br.com.rr.mastertech.cartao.controller;

import br.com.rr.mastertech.cartao.domain.Cartao;
import br.com.rr.mastertech.cartao.dto.request.CreateCartaoDTO;
import br.com.rr.mastertech.cartao.dto.request.UpdateCartaoDTO;
import br.com.rr.mastertech.cartao.dto.response.CartaoDTO;
import br.com.rr.mastertech.cartao.mapper.CartaoMapper;
import br.com.rr.mastertech.cartao.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private CartaoMapper cartaoMapper;

    @PostMapping
    public ResponseEntity<CartaoDTO> create(@RequestBody CreateCartaoDTO createDTO) {
        Cartao entity = cartaoService.create(createDTO.getNumero(), createDTO.getClienteId());
        return new ResponseEntity(cartaoMapper.toDTO(entity), HttpStatus.CREATED);
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

    @GetMapping("/id/{id}")
    public ResponseEntity<CartaoDTO> findById(@PathVariable Integer id) {
        Cartao entity = cartaoService.findById(id);
        return ResponseEntity.ok(cartaoMapper.toDTO(entity));
    }
}
