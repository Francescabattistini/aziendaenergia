package team5.azienda.energia.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Provincia;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    Optional<Provincia> findByNome(String nome);

    Optional<Provincia> findBySigla(String sigla);

    Optional<Provincia> findByNomeLike(@NonNull String nome);

    @Query("SELECT p FROM Provincia p JOIN fetch p.comuni ")
    Page<Provincia> findAllJoined(Pageable pageable);
}