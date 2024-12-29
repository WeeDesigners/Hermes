package agh.edu.hermes.persistance.repositories;

import agh.edu.hermes.persistance.entities.SlaRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaRuleRepository extends JpaRepository<SlaRule, Long> {}
