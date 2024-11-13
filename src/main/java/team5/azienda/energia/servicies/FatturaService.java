package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Fattura;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.payloadDTO.FatturaDTO;
import team5.azienda.energia.repositories.FatturaRepo;

import java.util.Optional;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepo fatturaRepo;

    public Fattura saveFattura(FatturaDTO body) {
        this.fatturaRepo.findBynumero(body.numero()).ifPresent(
                user -> {
                    throw new BadRequestException("Il numero fattura scritto  " + body.numero() + " è già in uso!");
                }
        );
        Fattura newUser = new Fattura(
                body.dataFattura(),
                body.importo(),
                body.numero(),
                body.cliente(),
                body.statoFattura());

        return this.fatturaRepo.save(newUser);
    }

    public Page<Fattura> findAllFatture(int size, int page, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepo.findAll(pageable);
    }

    public Optional<Fattura> findById(long id) {
        return fatturaRepo.findById(id);
    }

    public Fattura updateFattura(long id, FatturaDTO body) {
        return fatturaRepo.findById(id).map(fattura -> {
            fattura.setDataFattura(body.dataFattura());
            fattura.setImporto(body.importo());
            fattura.setNumero(body.numero());
            fattura.setCliente(body.cliente());
            fattura.setStatoFattura(body.statoFattura());
            return fatturaRepo.save(fattura);
        }).orElseThrow(() -> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id) {
        Fattura fattura = fatturaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        fatturaRepo.delete(fattura);
    }


}
