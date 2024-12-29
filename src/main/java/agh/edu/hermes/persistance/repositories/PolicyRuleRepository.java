package agh.edu.hermes.persistance.repositories;

import agh.edu.hermes.persistance.entities.PolicyRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRuleRepository extends JpaRepository<PolicyRule, Long> {}
