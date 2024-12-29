package agh.edu.hermes.persistance.repositories;

import agh.edu.hermes.persistance.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {}
