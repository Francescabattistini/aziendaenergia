package team5.azienda.energia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.azienda.energia.entities.StatoFattura;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
}