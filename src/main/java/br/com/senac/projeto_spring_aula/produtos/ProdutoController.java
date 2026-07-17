package br.com.senac.projeto_spring_aula.produtos;


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
    public ResponseEntity<ProdutoEntity> criarProduto(@Valid @RequestBody ProdutoDto dto){
        ProdutoEntity produto = new ProdutoEntity();

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());
        produto.setStatus(ProdutoStatus.DISPONIVEL);

        ProdutoEntity saved = produtoRepository.save(produto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> listarProdutos(){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getById(@PathVariable int id) {

        Optional<ProdutoEntity> byId = produtoRepository.findById(id);

        if (byId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(byId.get());
        }
    }

    @PatchMapping("/{id}/reabastecer")
    public  ResponseEntity<ProdutoEntity> resupplyProduct(@PathVariable int id,
    @RequestParam int quantidade){
        Optional<ProdutoEntity> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ProdutoEntity produtoEntity = optionalProduto.get();

        produtoEntity.setQuantidadeEstoque(produtoEntity.getQuantidadeEstoque() + quantidade);

        if (produtoEntity.getStatus().equals(ProdutoStatus.ESGOTADO)
                && produtoEntity.getQuantidadeEstoque() > 0) {
            produtoEntity.setStatus(ProdutoStatus.DISPONIVEL);
        }



        ProdutoEntity produtoAlterado = produtoRepository.save(produtoEntity);

        return ResponseEntity.ok(produtoAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
