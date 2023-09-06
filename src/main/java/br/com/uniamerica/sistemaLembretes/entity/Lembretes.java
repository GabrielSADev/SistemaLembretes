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

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Lembretes(){}

    public Lembretes(String lembrete, Long id) {
        this.lembrete = lembrete;
        this.id = id;
    }
}
