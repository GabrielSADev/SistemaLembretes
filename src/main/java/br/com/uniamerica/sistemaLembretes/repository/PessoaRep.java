package br.com.uniamerica.sistemaLembretes.repository;
import br.com.uniamerica.sistemaLembretes.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRep  extends JpaRepository<Pessoa, Long> {
}
