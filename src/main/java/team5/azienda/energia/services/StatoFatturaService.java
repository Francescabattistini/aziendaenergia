package team5.azienda.energia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team5.azienda.energia.entities.StatoFattura;
import team5.azienda.energia.repositories.StatoFatturaRepository;

import java.util.Optional;

@Service
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    /**
     * Salva un nuovo StatoFattura.
     *
     * @param statoFattura L'oggetto StatoFattura da salvare.
     * @return Lo StatoFattura salvato.
     */
    @Transactional
    public StatoFattura save(StatoFattura statoFattura) {
        return statoFatturaRepository.save(statoFattura);
    }

    /**
     * Recupera tutti gli StatiFattura con paginazione e ordinamento.
     *
     * @param page   Il numero della pagina.
     * @param size   La dimensione della pagina.
     * @param sortBy Il campo su cui ordinare.
     * @return Una pagina di StatiFattura.
     */
    public Page<StatoFattura> findAllStatiFattura(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return statoFatturaRepository.findAll(pageable);
    }

    /**
     * Trova uno StatoFattura per nome o ne crea uno nuovo se non esiste.
     *
     * @param stato Il nome dello stato.
     * @return Lo StatoFattura trovato o creato.
     */
    @Transactional
    public StatoFattura findByStatoOrSaveNew(String stato) {
        Optional<StatoFattura> found = statoFatturaRepository.findStatoFatturaByStato(stato);
        if (found.isPresent()) {
            return found.get();
        } else {
            StatoFattura nuovoStatoFattura = new StatoFattura(stato);
            return statoFatturaRepository.save(nuovoStatoFattura);
        }
    }
}
