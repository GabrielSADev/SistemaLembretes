package br.com.uniamerica.sistemaLembretes.service;
import br.com.uniamerica.sistemaLembretes.entity.Lembretes;
import br.com.uniamerica.sistemaLembretes.repository.LembretesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class LembretesService {

    @Autowired
    private LembretesRep lembretesRep;


    @Transactional(rollbackFor = Exception.class)
    public void cadastrarLembrete(final Lembretes lembretes){
        Assert.isTrue(!lembretes.getLembrete().equals(""),"O lembrete não pode ser nulo!!!");

        this.lembretesRep.save(lembretes);
    }

    public void atualizarLembrete (Lembretes lembretes){
        final Lembretes lembretesAttService=this.lembretesRep.findById(lembretes.getId()).orElse(null);

        this.lembretesRep.save(lembretes);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirLembrete(final Long id){

    final Lembretes lembretesBranco = this.lembretesRep.findById(id).orElse(null);

        if (lembretesBranco == null || !lembretesBranco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o modelo informado.");
        }

        this.lembretesRep.delete(lembretesBranco);

    }


}
