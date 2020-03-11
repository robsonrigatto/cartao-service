package br.com.rr.mastertech.pagamento.service;

import br.com.rr.mastertech.cartao.domain.Cartao;
import br.com.rr.mastertech.pagamento.domain.Pagamento;
import br.com.rr.mastertech.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento create(String descricao, Cartao cartao, Double valor) {
        Pagamento entity = Pagamento.builder().descricao(descricao).cartao(cartao).valor(valor).build();
        return pagamentoRepository.save(entity);
    }

    public List<Pagamento> findAllByIdCartao(Integer idCartao) {
        return pagamentoRepository.findAllByIdCartao(idCartao);
    }

}
