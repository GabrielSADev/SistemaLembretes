package br.com.uniamerica.sistemaLembretes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoa", schema = "public")
public class Pessoa extends AbstractEntity {

    @Getter @Setter
    @Column(name = "pessoa",nullable = false, unique = true)
    private String nomePessoa;
}
