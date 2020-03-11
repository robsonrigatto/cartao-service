package br.com.rr.mastertech.pagamento.controller;

import br.com.rr.mastertech.cartao.domain.Cartao;
import br.com.rr.mastertech.cartao.exception.InactivedEntityException;
import br.com.rr.mastertech.cartao.service.CartaoService;
import br.com.rr.mastertech.pagamento.domain.Pagamento;
import br.com.rr.mastertech.pagamento.dto.request.CreatePagamentoDTO;
import br.com.rr.mastertech.pagamento.dto.response.PagamentoDTO;
import br.com.rr.mastertech.pagamento.mapper.PagamentoMapper;
import br.com.rr.mastertech.pagamento.service.PagamentoService;
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
