package br.com.rr.mastertech.pagamento.domain;

import br.com.rr.mastertech.cartao.domain.Cartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue
    private Integer id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_CARTAO")
    private Cartao cartao;

    private Double valor;
}
