package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.StatoFattura;
import team5.azienda.energia.repositories.StatoFatturaRepository;

import java.util.Optional;

@Service
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public StatoFattura save(StatoFattura statoFattura) {
        return statoFatturaRepository.save(statoFattura);
    }

    public Page<StatoFattura> findAllStatiFattura(int size, int page, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return statoFatturaRepository.findAll(pageable);
    }

    public StatoFattura findByStatoOrSaveNew(String stato) {
        Optional<StatoFattura> found = statoFatturaRepository.findStatoFatturaByStato(stato);
        if (found.isPresent()) {
            return found.get();
        } else {
            StatoFattura statoFatturaToSave = new StatoFattura(stato);
            return statoFatturaToSave;
        }
    }
    /*
    //DA MODIFICARE POI
    public StatoFattura updateStatoFattura(Long id, StatoFattura statoFatturaDetails) {
        return statoFatturaRepository.findById(id).map(statoFattura -> {
            statoFattura.setStato(statoFatturaDetails.getStato());
            statoFattura.setFatture(statoFatturaDetails.getFatture());
            return statoFatturaRepository.save(statoFattura);
        }).orElseThrow(() -> new NotFoundException("Stato Fattura non trovato con ID: " + id));
    }

   //DA MODIFICARE POI
    public void findByIdAndDelete(Long id) {
        StatoFattura statoFattura = statoFatturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Stato Fattura non trovato con ID: " + id));
        statoFatturaRepository.delete(statoFattura);
    }
    */

}
