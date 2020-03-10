package br.com.rr.mastertech.cartoes.controller;

import br.com.rr.mastertech.cartoes.domain.Cartao;
import br.com.rr.mastertech.cartoes.domain.Pagamento;
import br.com.rr.mastertech.cartoes.dto.request.CreatePagamentoDTO;
import br.com.rr.mastertech.cartoes.dto.response.PagamentoDTO;
import br.com.rr.mastertech.cartoes.exception.InactivedEntityException;
import br.com.rr.mastertech.cartoes.mapper.PagamentoMapper;
import br.com.rr.mastertech.cartoes.service.CartaoService;
import br.com.rr.mastertech.cartoes.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<PagamentoDTO> create(@RequestBody CreatePagamentoDTO createDTO) {
        try {
            Cartao cartao = cartaoService.findActiveById(createDTO.getIdCartao());
            Pagamento entity = pagamentoService.create(createDTO.getDescricao(), cartao, createDTO.getValor());
            return new ResponseEntity<>(pagamentoMapper.toDTO(entity), HttpStatus.CREATED);

        } catch (EntityNotFoundException ex) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "é obrigatório informar um cartão existente");

        } catch (InactivedEntityException ex) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "é obrigatório informar um cartão ativo");
        }
    }

    @GetMapping("/{idCartao}")
    public ResponseEntity<List<PagamentoDTO>> findAllByIdCartao(@PathVariable Integer idCartao) {
        Cartao cartao = cartaoService.findById(idCartao);
        List<Pagamento> entities = pagamentoService.findAllByIdCartao(cartao.getId());
        List<PagamentoDTO> dtos = entities.stream().map(e -> pagamentoMapper.toDTO(e))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
