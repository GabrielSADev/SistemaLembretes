package br.com.uniamerica.sistemaLembretes.repository;
import br.com.uniamerica.sistemaLembretes.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRep  extends JpaRepository<Pessoa, Long> {
    Pessoa findByNomePessoa(String nomePessoa);
}
