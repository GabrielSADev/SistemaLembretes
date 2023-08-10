package br.com.uniamerica.sistemaLembretes.service;


import br.com.uniamerica.sistemaLembretes.entity.Pessoa;
import br.com.uniamerica.sistemaLembretes.repository.PessoaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PessoaService {

    @Autowired
    PessoaRep pessoaRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarPessoa(final Pessoa pessoa){
        Assert.isTrue(!pessoa.getNomePessoa().equals(""),"Nome da pessoa não pode ser nulo!!");
            this.pessoaRep.save(pessoa);
    }

    public void atualizaPessoa (Pessoa pessoa){
        final Pessoa pessoaAttService=this.pessoaRep.findById(pessoa.getId()).orElse(null);
        this.pessoaRep.save(pessoa);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirPessoa(final Long id) {

    final Pessoa pessoaBanco = this.pessoaRep.findById(id).orElse(null);

        if (pessoaBanco == null || !pessoaBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

    this.pessoaRep.delete(pessoaBanco);


    }
}









