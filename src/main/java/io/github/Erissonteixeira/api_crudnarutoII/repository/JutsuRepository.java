package io.github.Erissonteixeira.api_crudnarutoII.repository;

import io.github.Erissonteixeira.api_crudnarutoII.model.Jutsu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JutsuRepository extends JpaRepository<Jutsu, Long> {
}
