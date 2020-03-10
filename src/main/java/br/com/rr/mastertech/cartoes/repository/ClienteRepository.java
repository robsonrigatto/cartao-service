package br.com.rr.mastertech.cartoes.repository;

import br.com.rr.mastertech.cartoes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    //
}
