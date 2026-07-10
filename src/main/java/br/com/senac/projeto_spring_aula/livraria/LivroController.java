package br.com.senac.projeto_spring_aula.livraria;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<LivroEntity>  criarLivro(@Valid @RequestBody LivroPostDto dto){
        LivroEntity livro = new LivroEntity();

        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setAnoPublicacao(dto.anoPublicacao());

        LivroEntity livroEntity = livroRepository.save(livro);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroEntity);
    }

    @GetMapping
    public ResponseEntity<List<LivroEntity>> listarLivros(){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(livroRepository.findAll());
    }

}
