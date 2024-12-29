package agh.edu.hermes.persistance.repositories;

import agh.edu.hermes.persistance.entities.Sla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaRepository extends JpaRepository<Sla, Long> {}
