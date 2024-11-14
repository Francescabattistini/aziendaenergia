package team5.azienda.energia.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Comune;

import java.util.Optional;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
    Optional<Comune> findByNome(String nome);
    @Query("SELECT c FROM Comune c left JOIN fetch c.provincia ")
    Page<Comune> findAllAndProvincia(Pageable pageable);
}