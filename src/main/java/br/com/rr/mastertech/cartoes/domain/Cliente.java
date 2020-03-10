package br.com.rr.mastertech.cartoes.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
