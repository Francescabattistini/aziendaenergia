package team5.azienda.energia.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team5.azienda.energia.entities.Cliente;

public interface ClienteRepo extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(Double fatturatoAnnuale, Pageable pageable);

    Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);

    Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);

    Page<Cliente> findByNomeContattoContainingIgnoreCase(String parteNome, Pageable pageable);
}

