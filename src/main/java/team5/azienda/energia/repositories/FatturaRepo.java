package team5.azienda.energia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.azienda.energia.entities.Fattura;

@Repository
public interface FatturaRepo extends JpaRepository<Fattura, Long> {
}
