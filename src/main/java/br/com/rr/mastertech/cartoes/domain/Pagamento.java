package br.com.rr.mastertech.cartoes.domain;

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
