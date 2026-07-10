package br.com.senac.projeto_spring_aula.todolist;


import br.com.senac.projeto_spring_aula.todolist.model.ListaEntity;
import br.com.senac.projeto_spring_aula.todolist.model.ListaPostDto;
import br.com.senac.projeto_spring_aula.todolist.model.ListaStatus;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lista")
@RequiredArgsConstructor
public class ToDoListController {

    private final ListaRepository listaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ListaEntity> createTask(@Valid @RequestBody ListaPostDto dto){
        ListaEntity listaEntity = new ListaEntity();

        listaEntity.setDescricao(dto.descricao());
        listaEntity.setStatus(ListaStatus.TO_DO);

        ListaEntity salva =  listaRepository.save(listaEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(salva);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<ListaEntity>> getAll(){
        List<ListaEntity>  todas = listaRepository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todas);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ListaEntity> getById(@PathVariable int id) {

        Optional<ListaEntity> optionalListaEntity = listaRepository.findById(id);

        if (optionalListaEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalListaEntity.get());
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public  ResponseEntity<ListaEntity> endTask(@PathVariable int id){
       Optional<ListaEntity> optionalLista = listaRepository.findById(id);

       if (optionalLista.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }

       ListaEntity listaEntity = optionalLista.get();

       if (!listaEntity.getStatus().equals(ListaStatus.TO_DO)){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       }

       listaEntity.setStatus(ListaStatus.DONE);
       listaEntity.setDataConclusao(LocalDateTime.now());

       ListaEntity listaAlterada = listaRepository.save(listaEntity);

       return ResponseEntity.status(HttpStatus.OK).body(listaAlterada);
    }

    @PatchMapping("/{id}/cancelar")
    @Transactional
    public ResponseEntity<ListaEntity> cancelTask(@PathVariable int id) {
        Optional<ListaEntity> optionalLista = listaRepository.findById(id);

        if (optionalLista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ListaEntity listaEntity = optionalLista.get();
        if (!listaEntity.getStatus().equals(ListaStatus.TO_DO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        listaEntity.setStatus(ListaStatus.CANCELLED);

        ListaEntity listaAlterada = listaRepository.save(listaEntity);

        return ResponseEntity.status(HttpStatus.OK).body(listaAlterada);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        listaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
