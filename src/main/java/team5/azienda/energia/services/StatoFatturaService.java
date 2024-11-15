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

    @Transactional
    public StatoFattura save(StatoFattura statoFattura) {
        return statoFatturaRepository.save(statoFattura);
    }

    public Page<StatoFattura> findAllStatiFattura(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return statoFatturaRepository.findAll(pageable);
    }


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
