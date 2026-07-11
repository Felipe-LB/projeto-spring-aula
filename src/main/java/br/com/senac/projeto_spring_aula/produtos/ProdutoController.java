package br.com.senac.projeto_spring_aula.produtos;


import br.com.senac.projeto_spring_aula.livraria.LivroEntity;
import br.com.senac.projeto_spring_aula.livraria.LivroPostDto;
import br.com.senac.projeto_spring_aula.livraria.LivroRepository;
import br.com.senac.projeto_spring_aula.todolist.model.ListaEntity;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoEntity> criarProduto(@Valid @RequestBody ProdutoPostDto dto){
        ProdutoEntity produto = new ProdutoEntity();

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());
        produto.setStatus(ProdutoStatus.DISPONIVEL);

        ProdutoEntity produtoEntity = produtoRepository.save(produto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoEntity);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> listarProdutos(){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getById(@PathVariable int id) {

        Optional<ProdutoEntity> optionalProdutoEntity = produtoRepository.findById(id);

        if (optionalProdutoEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalProdutoEntity.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
