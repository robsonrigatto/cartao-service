package br.com.rr.mastertech.cartoes.controller;

import br.com.rr.mastertech.cartoes.domain.Cartao;
import br.com.rr.mastertech.cartoes.domain.Pagamento;
import br.com.rr.mastertech.cartoes.dto.request.CreatePagamentoDTO;
import br.com.rr.mastertech.cartoes.dto.response.PagamentoDTO;
import br.com.rr.mastertech.cartoes.mapper.PagamentoMapper;
import br.com.rr.mastertech.cartoes.repository.CartaoRepository;
import br.com.rr.mastertech.cartoes.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping
    public ResponseEntity<PagamentoDTO> create(@RequestBody CreatePagamentoDTO createDTO) {
        Optional<Cartao> optionalCartao = cartaoRepository.findById(createDTO.getIdCartao());
        if(!optionalCartao.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "é obrigatório informar um cartão existente");
        }

        Cartao cartao = optionalCartao.get();
        if(!cartao.getAtivo()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "é obrigatório informar um cartão ativo");
        }

        Pagamento entity = Pagamento.builder().descricao(createDTO.getDescricao()).cartao(cartao).valor(createDTO.getValor()).build();
        entity = pagamentoRepository.save(entity);

        return new ResponseEntity<>(pagamentoMapper.entityToDTO(entity), HttpStatus.CREATED);
    }

    @GetMapping("/{idCartao}")
    public ResponseEntity<List<PagamentoDTO>> findAllByIdCartao(@PathVariable Integer idCartao) {
        Optional<Cartao> optionalCartao = cartaoRepository.findById(idCartao);
        if(!optionalCartao.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "é obrigatório informar um cartão existente");
        }

        List<Pagamento> entities = pagamentoRepository.findAllByIdCartao(idCartao);
        List<PagamentoDTO> dtos = entities.stream().map(e -> pagamentoMapper.entityToDTO(e))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
