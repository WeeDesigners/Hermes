package agh.edu.hermes.repositories;

import agh.edu.hermes.types.Sla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaRepository extends JpaRepository<Sla, Long> {}
