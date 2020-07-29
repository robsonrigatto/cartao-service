package br.com.rr.mastertech.cartao.service;

import br.com.rr.mastertech.cartao.client.ClienteClient;
import br.com.rr.mastertech.cartao.client.dto.ClienteDTO;
import br.com.rr.mastertech.cartao.client.exception.ClienteOfflineException;
import br.com.rr.mastertech.cartao.domain.Cartao;
import br.com.rr.mastertech.cartao.exception.CartaoNaoEncontradoException;
import br.com.rr.mastertech.cartao.exception.ClienteNaoEncontradoException;
import br.com.rr.mastertech.cartao.exception.NumeroCartaoDuplicadoException;
import br.com.rr.mastertech.cartao.mapper.CartaoMapper;
import br.com.rr.mastertech.cartao.repository.CartaoRepository;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoMapper mapper;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteClient clienteClient;

    public Cartao create(String numero, Integer clienteId) {
        try {
            ClienteDTO clienteDTO = clienteClient.findById(clienteId);
            Cartao cartao = mapper.toCartao(numero, clienteDTO, false);
            return this.cartaoRepository.save(cartao);

        } catch (HystrixRuntimeException ex) {
            if(ex.getCause() instanceof FeignException.NotFound) {
                throw new ClienteNaoEncontradoException();
            }
            throw new ClienteOfflineException();

        } catch (DataIntegrityViolationException ex) {
            throw new NumeroCartaoDuplicadoException();
        }
    }

    public Cartao update(Integer id, Boolean ativo) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findById(id);
        return update(ativo, optionalCartao);
    }

    public Cartao update(String numero, Boolean ativo) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findByNumero(numero);
        return update(ativo, optionalCartao);
    }

    public Cartao findById(Integer id) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findById(id);
        if(!optionalCartao.isPresent()) {
            throw new CartaoNaoEncontradoException();
        }

        return optionalCartao.get();
    }

    public Cartao findByNumero(String numero) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findByNumero(numero);
        if(!optionalCartao.isPresent()) {
            throw new CartaoNaoEncontradoException();
        }

        return optionalCartao.get();
    }

    private Cartao update(Boolean ativo, Optional<Cartao> optionalCartao) {
        if (!optionalCartao.isPresent()) {
            throw new CartaoNaoEncontradoException();
        }

        Cartao entity = optionalCartao.get();
        entity.setAtivo(ativo);
        return cartaoRepository.save(entity);
    }
}
