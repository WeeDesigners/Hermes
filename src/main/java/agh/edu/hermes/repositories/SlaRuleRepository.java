package agh.edu.hermes.repositories;

import agh.edu.hermes.types.SlaRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaRuleRepository extends JpaRepository<SlaRule, Long> {}
