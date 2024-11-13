package team5.azienda.energia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.azienda.energia.entities.Fattura;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FatturaRepo extends JpaRepository<Fattura, Long> {
    Optional<Fattura> findBynumero(Integer numero);

    List<Fattura> findByClienteId(Long cliente_id);

    List<Fattura> findByDataFattura(LocalDate dataFattura);

    List<Fattura> findByStatoFatturaStato(String stato);
}
