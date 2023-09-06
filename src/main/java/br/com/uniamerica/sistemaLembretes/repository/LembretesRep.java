package br.com.uniamerica.sistemaLembretes.repository;

import br.com.uniamerica.sistemaLembretes.entity.Lembretes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LembretesRep extends JpaRepository <Lembretes, Long> {

}
