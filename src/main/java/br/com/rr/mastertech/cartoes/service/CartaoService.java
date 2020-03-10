package br.com.rr.mastertech.cartoes.service;

import br.com.rr.mastertech.cartoes.domain.Cartao;
import br.com.rr.mastertech.cartoes.domain.Cliente;
import br.com.rr.mastertech.cartoes.exception.InactivedEntityException;
import br.com.rr.mastertech.cartoes.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao create(String numero, Cliente cliente) {
        Cartao cartao = Cartao.builder().numero(numero).cliente(cliente).ativo(false).build();
        return this.cartaoRepository.save(cartao);
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
