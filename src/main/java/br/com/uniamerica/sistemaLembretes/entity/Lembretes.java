package br.com.uniamerica.sistemaLembretes.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lembretes",schema = "public")
public class Lembretes extends  AbstractEntity{

    @Getter @Setter
    @Column(name = "lembrete")
    private String lembrete;

    @Setter
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
