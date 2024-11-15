package team5.azienda.energia.services;

import org.springframework.beans.factory.annotation.Autowired;
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
