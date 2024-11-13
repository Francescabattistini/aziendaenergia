package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Indirizzo;
import team5.azienda.energia.repositories.IndirizzoRepo;

import java.util.Optional;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepo indirizzoRepo;

    //DA MODIFICARE POI
    public Indirizzo save(Indirizzo indirizzo) {
        return indirizzoRepo.save(indirizzo);
    }

    public Page<Indirizzo> findAllIndirizzi(int size, int page, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return indirizzoRepo.findAll(pageable);
    }

    public Optional<Indirizzo> findById(long id) {
        return indirizzoRepo.findById(id);
    }

     //DA MODIFICARE POI
    /* public Indirizzo updateIndirizzo(long id, Indirizzo indirizzoDetails) {
        return indirizzoRepo.findById(id).map(indirizzo -> {
            indirizzo.setVia(indirizzoDetails.getVia());
            indirizzo.setCivico(indirizzoDetails.getCivico());
            indirizzo.setCliente(indirizzoDetails.getCliente());
            indirizzo.setComune(indirizzoDetails.getComune());
            return indirizzoRepo.save(indirizzo);
        }).orElseThrow(() -> new NotFoundException("Indirizzo non trovato con ID: " + id));
    }

      //DA MODIFICARE POI
     public void findByIdAndDelete(long id) {
        Indirizzo indirizzo = indirizzoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Indirizzo non trovato con ID: " + id));
        indirizzoRepo.delete(indirizzo);
    }
     */

}
