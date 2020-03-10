package br.com.rr.mastertech.cartoes.repository;

import br.com.rr.mastertech.cartoes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findById(Integer id);
}
