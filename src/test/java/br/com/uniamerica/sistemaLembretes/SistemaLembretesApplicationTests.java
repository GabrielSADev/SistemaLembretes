package br.com.uniamerica.sistemaLembretes;
import br.com.uniamerica.sistemaLembretes.controller.LembretesController;
import br.com.uniamerica.sistemaLembretes.controller.PessoaController;
import br.com.uniamerica.sistemaLembretes.entity.Lembretes;
import br.com.uniamerica.sistemaLembretes.entity.Pessoa;
import br.com.uniamerica.sistemaLembretes.repository.LembretesRep;
import br.com.uniamerica.sistemaLembretes.repository.PessoaRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SistemaLembretesApplicationTests {

    @MockBean
    PessoaRep pessoaRep;
    @Autowired
    private final PessoaController controller = new PessoaController();
    private List<Pessoa> pessoaList;
    @MockBean
    LembretesRep lembretesRep;

    @Autowired
    private final LembretesController lembretesController = new LembretesController();

    @BeforeEach
    void injectData(){
        Optional<Pessoa> pessoa = Optional.of(new Pessoa("Gabriel", 1L));
        Long id = 1L;
        Mockito.when(pessoaRep.findById(id)).thenReturn(pessoa);
    }
    @BeforeEach
    void injectLembrete(){
        Optional<Lembretes> lembretes = Optional.of(new Lembretes("Teste_De_Lembrete", 2L));
        Long id = 2L;
        Mockito.when(lembretesRep.findById(id)).thenReturn(lembretes);
    }

    @BeforeEach
    void injectListaData(){

        pessoaList = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa("Willian", 1L);
        Pessoa pessoa2 = new Pessoa("Douglas", 2L);
        Pessoa pessoa3 = new Pessoa("Gabriel", 3L);

        pessoaList.add(pessoa1);
        pessoaList.add(pessoa2);
        pessoaList.add(pessoa3);

        Mockito.when(pessoaRep.findAll()).thenReturn(pessoaList);

        ReflectionTestUtils.setField(controller, "pessoaRep", pessoaRep);

    }


    @Test
    void testControllerPessoa(){
        var pessoaController = controller.findByIdPath(1L);
        Long id = pessoaController.getBody().getId().longValue();
        System.out.println(id);
        Assert.assertEquals(1L, id,0);
    }

    @Test
    void testControllerLembrete(){
        var lembreteController = lembretesController.findByIdPath(2L);
        Long id = lembreteController.getBody().getId().longValue();
        System.out.println(id);
        Assert.assertEquals(2l,id,0);
    }

    @Test
    void testSimplesLembrete(){
        Lembretes lembretes = new Lembretes();

        lembretes.setLembrete("Lembrete De Teste");

        Assert.assertEquals("Lembrete De Teste",lembretes.getLembrete());
    }

    @Test
    void testPessoaList() {
        ResponseEntity<List<Pessoa>> pessoaController = controller.ListaPessoa();
        List<Pessoa> pessoaListController = pessoaController.getBody();

        Assert.assertNotNull(pessoaListController);
        Assert.assertEquals(pessoaList.size(), pessoaListController.size());

        for (int i = 0; i < pessoaList.size(); i++) {
            Assert.assertEquals(pessoaList.get(i), pessoaListController.get(i));
        }

    }

    @Test
    void testSimplesPessoa(){
        Pessoa pessoa = new Pessoa();

        pessoa.setNomePessoa("Adalberto");

        Assert.assertEquals("Adalberto", pessoa.getNomePessoa());
    }
}
