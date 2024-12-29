package agh.edu.hermes.repositories;

import agh.edu.hermes.types.attributes.Clause;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Clause, Long> {}
