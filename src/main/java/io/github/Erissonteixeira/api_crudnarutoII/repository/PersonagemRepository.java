package io.github.Erissonteixeira.api_crudnarutoII.repository;

import io.github.Erissonteixeira.api_crudnarutoII.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}
