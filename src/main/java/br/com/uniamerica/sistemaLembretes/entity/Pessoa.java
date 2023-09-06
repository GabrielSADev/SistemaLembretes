package br.com.uniamerica.sistemaLembretes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pessoa", schema = "public")
public class Pessoa extends AbstractEntity {

    @Getter @Setter
    @Column(name = "pessoa",nullable = false, unique = true)
    private String nomePessoa;

    @Getter @Setter
    @Column(name = "id_Pessoa")
    private Long id;

    @Getter @Setter
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Lembretes> lembretes;

    public Pessoa(){}
    public Pessoa(String nomePessoa, Long id) {
        this.nomePessoa = nomePessoa;
        this.id = id;
    }
}
