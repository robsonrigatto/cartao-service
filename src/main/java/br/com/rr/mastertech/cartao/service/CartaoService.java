package br.com.rr.mastertech.cartao.service;

import br.com.rr.mastertech.cartao.client.ClienteClient;
import br.com.rr.mastertech.cartao.client.dto.ClienteDTO;
import br.com.rr.mastertech.cartao.domain.Cartao;
import br.com.rr.mastertech.cartao.exception.ClienteNaoEncontradoException;
import br.com.rr.mastertech.cartao.exception.InactivedEntityException;
import br.com.rr.mastertech.cartao.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteClient clienteClient;

    public Cartao create(String numero, Integer clienteId) {
        try {
            ClienteDTO clienteDTO = clienteClient.findById(clienteId);
            Cartao cartao = Cartao.builder().numero(numero).clienteId(clienteDTO.getId()).ativo(false).build();
            return this.cartaoRepository.save(cartao);

        } catch (FeignException.FeignClientException.NotFound ex) {
            throw new ClienteNaoEncontradoException();
        }
    }

    public Cartao update(String numero, Boolean ativo) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findByNumero(numero);
        if(!optionalCartao.isPresent()) {
            throw new EntityNotFoundException();
        }

        Cartao entity = optionalCartao.get();
        entity.setAtivo(ativo);
        return cartaoRepository.save(entity);
    }

    public Cartao findActiveById(Integer id) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findById(id);
        if(!optionalCartao.isPresent()) {
            throw new EntityNotFoundException();
        }

        Cartao cartao = optionalCartao.get();
        if(!cartao.getAtivo()) {
            throw new InactivedEntityException();
        }

        return cartao;
    }

    public Cartao findById(Integer id) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findById(id);
        if(!optionalCartao.isPresent()) {
            throw new EntityNotFoundException();
        }

        return optionalCartao.get();
    }

    public Cartao findByNumero(String numero) {
        Optional<Cartao> optionalCartao = this.cartaoRepository.findByNumero(numero);
        if(!optionalCartao.isPresent()) {
            throw new EntityNotFoundException();
        }

        return optionalCartao.get();
    }
}
