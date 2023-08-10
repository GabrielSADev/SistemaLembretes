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

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "lembretes_pessoa",
        uniqueConstraints = @UniqueConstraint(
                columnNames ={
                    "lembretes_id","pessoa_id"
                }
        ),
            joinColumns = @JoinColumn(
                    name = "lembretes_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "pessoa_Id"
            )
    )
    private Pessoa pessoa;
}
