package br.com.rr.mastertech.cartoes.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cartao {

    @Id
    @GeneratedValue
    private Integer id;

    private String numero;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;
}
