package agh.edu.hermes.repositories;

import agh.edu.hermes.types.Policies;
import agh.edu.hermes.types.PolicyRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliciesRepository extends JpaRepository<Policies, Long> {}
