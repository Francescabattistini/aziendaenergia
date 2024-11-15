package team5.azienda.energia.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team5.azienda.energia.entities.Cliente;

import java.time.LocalDate;
import java.util.Optional;

public interface ClienteRepo extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(Double fatturatoAnnuale, Pageable pageable);

    Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);

    Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);

    Page<Cliente> findByNomeContattoContainingIgnoreCase(String parteNome, Pageable pageable);

}

