package br.com.uniamerica.sistemaLembretes.controller;


import br.com.uniamerica.sistemaLembretes.entity.Lembretes;
import br.com.uniamerica.sistemaLembretes.repository.LembretesRep;
import br.com.uniamerica.sistemaLembretes.service.LembretesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ( value =  "/api/lembrete")
public class LembretesController {

        @Autowired
        private LembretesRep lembretesRep;
        @Autowired
        private LembretesService lembretesService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Lembretes lembretes = this.lembretesRep.findById(id).orElse(null);
        return ResponseEntity.ok(lembretes);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.lembretesRep.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final Lembretes lembretes){
        try {
            lembretesService.cadastrarLembrete(lembretes);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Lembretes lembretes){
        try {
            lembretesService.atualizarLembrete(lembretes);
            final Lembretes lembretes1 = this.lembretesRep.findById(id).orElse(null);

            if (lembretes1 == null || !lembretes1.getId().equals(lembretes.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
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
    public ResponseEntity<?> deletaLembrete(@PathVariable("id") final Long id){
        try {
            this.lembretesService.excluirLembrete(id);
            return ResponseEntity.ok("Lembrete deletada com sucesso");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
