package br.com.rr.mastertech.cartao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String numero;

    @Column(columnDefinition = "boolean default false")
    private Boolean ativo;

    @Column(name = "ID_CLIENTE", nullable = false)
    private Integer clienteId;
}
