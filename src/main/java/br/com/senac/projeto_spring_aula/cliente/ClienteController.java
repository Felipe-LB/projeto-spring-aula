package br.com.senac.projeto_spring_aula.cliente;

import br.com.senac.projeto_spring_aula.produtos.ProdutoEntity;
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
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteEntity> criarCliente(@Valid @RequestBody ClienteDto dto){
        ClienteEntity cliente = new ClienteEntity();

        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());
        cliente.setDataCadastro(LocalDateTime.now());

        ClienteEntity clienteEntity = clienteRepository.save(cliente);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteEntity);
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> listarClientes(){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(clienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getById(@PathVariable int id) {

        Optional<ClienteEntity> optionalClienteEntity = clienteRepository.findById(id);

        if (optionalClienteEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalClienteEntity.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
