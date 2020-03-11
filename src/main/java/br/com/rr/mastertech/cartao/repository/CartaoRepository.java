package br.com.rr.mastertech.cartao.repository;

import br.com.rr.mastertech.cartao.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    Optional<Cartao> findByNumero(String numero);
}
