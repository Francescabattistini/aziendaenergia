package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Fattura;
import team5.azienda.energia.repositories.FatturaRepo;

import java.util.Optional;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepo fatturaRepo;

    //DA MODIFICARE POI
    public Fattura save(Fattura fattura) {
        return fatturaRepo.save(fattura);
    }

    public Page<Fattura> findAllFatture(int size, int page, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepo.findAll(pageable);
    }

    public Optional<Fattura> findById(long id) {
        return fatturaRepo.findById(id);
    }

    //DA MODIFICARE POI
   /* public Fattura updateFattura(long id, Fattura fatturaDetails) {
        return fatturaRepo.findById(id).map(fattura -> {
            fattura.setDataFattura(fatturaDetails.getDataFattura());
            fattura.setImporto(fatturaDetails.getImporto());
            fattura.setNumero(fatturaDetails.getNumero());
            fattura.setCliente(fatturaDetails.getCliente());
            fattura.setStatoFattura(fatturaDetails.getStatoFattura());
            return fatturaRepo.save(fattura);
        }).orElseThrow(() -> new NotFoundException("Fattura non trovata con ID: " + id));
    }

  //DA MODIFICARE POI
    public void findByIdAndDelete(long id) {
        Fattura fattura = fatturaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata con ID: " + id));
        fatturaRepo.delete(fattura);
    }*/

}
