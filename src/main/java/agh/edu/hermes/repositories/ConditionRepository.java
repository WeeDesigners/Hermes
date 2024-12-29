package agh.edu.hermes.repositories;

import agh.edu.hermes.types.attributes.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Long> {}
