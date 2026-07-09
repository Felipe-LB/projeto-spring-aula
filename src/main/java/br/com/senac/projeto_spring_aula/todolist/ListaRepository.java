package br.com.senac.projeto_spring_aula.todolist;

import br.com.senac.projeto_spring_aula.todolist.model.ListaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaRepository extends JpaRepository<ListaEntity, Integer> {

}
