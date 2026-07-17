package br.com.senac.projeto_spring_aula.cliente;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByEmail(String email);
    @Query("SELECT c FROM ClienteEntity c WHERE c.email = :email")
    boolean existsByEmail(String email);
}
