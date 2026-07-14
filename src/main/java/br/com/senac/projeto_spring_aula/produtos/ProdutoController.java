package br.com.senac.projeto_spring_aula.produtos;


import br.com.senac.projeto_spring_aula.livraria.LivroEntity;
import br.com.senac.projeto_spring_aula.livraria.LivroPostDto;
import br.com.senac.projeto_spring_aula.livraria.LivroRepository;
import br.com.senac.projeto_spring_aula.todolist.model.ListaEntity;
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
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoEntity> criarProduto(@Valid @RequestBody ProdutoDto dto){
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

    @PatchMapping("/{id}/reabastecer")
    @Transactional
    public  ResponseEntity<ProdutoEntity> resupplyTask(@PathVariable int id,
    @RequestParam int quantidade){
        Optional<ProdutoEntity> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ProdutoEntity produtoEntity = optionalProduto.get();

        produtoEntity.setQuantidadeEstoque(produtoEntity.getQuantidadeEstoque() + quantidade);

        if (produtoEntity.getStatus().equals(ProdutoStatus.ESGOTADO)
                && produtoEntity.getQuantidadeEstoque() > 0) {
            produtoEntity.setStatus(ProdutoStatus.DISPONIVEL);
        }



        ProdutoEntity ProdutoAlterado = produtoRepository.save(produtoEntity);

        return ResponseEntity.status(HttpStatus.OK).body(ProdutoAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
