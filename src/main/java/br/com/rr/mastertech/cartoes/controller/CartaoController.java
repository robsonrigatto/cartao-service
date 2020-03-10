package br.com.rr.mastertech.cartoes.controller;

import br.com.rr.mastertech.cartoes.domain.Cartao;
import br.com.rr.mastertech.cartoes.domain.Cliente;
import br.com.rr.mastertech.cartoes.dto.request.CreateCartaoDTO;
import br.com.rr.mastertech.cartoes.dto.request.UpdateCartaoDTO;
import br.com.rr.mastertech.cartoes.dto.response.CartaoDTO;
import br.com.rr.mastertech.cartoes.repository.CartaoRepository;
import br.com.rr.mastertech.cartoes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<CartaoDTO> create(@RequestBody CreateCartaoDTO createDTO) {
        Cartao entity = new Cartao();
        entity.setNumero(createDTO.getNumero());

        Optional<Cliente> optionalCliente = clienteRepository.findById(createDTO.getClienteId());
        if(!optionalCliente.isPresent()) { //TODO cliente não existe
            return ResponseEntity.unprocessableEntity().build();
        }

        entity.setCliente(optionalCliente.get());
        entity.setAtivo(false);
        entity = this.cartaoRepository.save(entity);

        CartaoDTO dto = CartaoDTO.builder().id(entity.getId()).numero(entity.getNumero())
                .clienteId(entity.getCliente().getId()).ativo(false).build();
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<CartaoDTO> findByNumero(@PathVariable String numero) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findByNumero(numero);
        if(!optionalCartao.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cartao entity = optionalCartao.get();
        CartaoDTO dto = CartaoDTO.builder().id(entity.getId()).numero(entity.getNumero())
                .clienteId(entity.getCliente().getId()).ativo(entity.getAtivo()).build();
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{numero}")
    public ResponseEntity<CartaoDTO> update(@PathVariable String numero, @RequestBody UpdateCartaoDTO updateDTO) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findByNumero(numero);
        if(!optionalCartao.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cartao entity = optionalCartao.get();
        entity.setAtivo(updateDTO.getAtivo());
        entity = cartaoRepository.save(entity);

        CartaoDTO dto = CartaoDTO.builder().id(entity.getId()).numero(entity.getNumero())
                .clienteId(entity.getCliente().getId()).ativo(entity.getAtivo()).build();
        return ResponseEntity.ok(dto);
    }
}
