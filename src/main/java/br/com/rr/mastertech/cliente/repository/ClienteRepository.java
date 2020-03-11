package br.com.rr.mastertech.cliente.repository;

import br.com.rr.mastertech.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    //
}
