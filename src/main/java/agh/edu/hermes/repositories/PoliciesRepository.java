package agh.edu.hermes.repositories;

import agh.edu.hermes.types.Policies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliciesRepository extends JpaRepository<Policies, Long> {}
