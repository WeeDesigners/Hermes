package agh.edu.hermes.persistance.repositories;

import agh.edu.hermes.persistance.entities.Policies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliciesRepository extends JpaRepository<Policies, Long> {}
