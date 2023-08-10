package br.com.uniamerica.sistemaLembretes.controller;
import br.com.uniamerica.sistemaLembretes.entity.Pessoa;
import br.com.uniamerica.sistemaLembretes.repository.PessoaRep;
import br.com.uniamerica.sistemaLembretes.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping (value = "/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRep pessoaRep;
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Pessoa pessoa = this.pessoaRep.findById(id).orElse(null);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/lista")
    public ResponseEntity <?> ListaPessoa(){
        return ResponseEntity.ok(this.pessoaRep.findAll());
    }

    @GetMapping("/nomePessoa")
    public ResponseEntity<?> buscarPorNome(@RequestParam("nomePessoa") String nomePessoa) {
        try {
           Pessoa pessoa1 = pessoaRep.findByNomePessoa(nomePessoa);
            if (pessoa1 != null) {
                return ResponseEntity.ok(pessoa1);
            } else {
                return ResponseEntity.internalServerError().body("Error: caiu no Else ");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity <?> cadastrarPessoa(@RequestBody final  Pessoa pessoa){
        try {
            pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editaPessoa(@PathVariable("id") final Long id, @RequestBody final Pessoa pessoa){
        try {
            pessoaService.atualizaPessoa(pessoa);
            final Pessoa pessoa1 = this.pessoaRep.findById(id).orElse(null);

            if (pessoa1 == null || !pessoa1.getId().equals(pessoa.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            return ResponseEntity.ok("Pessoa editada com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaPessoa(@PathVariable("id") final Long id){
        try {
            this.pessoaService.excluirPessoa(id);
            return ResponseEntity.ok("Pessoa deletada com sucesso");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}
