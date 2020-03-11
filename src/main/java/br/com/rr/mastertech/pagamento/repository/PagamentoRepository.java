package br.com.rr.mastertech.pagamento.repository;

import br.com.rr.mastertech.pagamento.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    @Query("select p from Pagamento p where p.cartao.id = :idCartao order by p.id asc")
    List<Pagamento> findAllByIdCartao(Integer idCartao);
}
