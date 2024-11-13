package team5.azienda.energia.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Fattura;
import team5.azienda.energia.entities.StatoFattura;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.payloadDTO.FatturaDTO;
import team5.azienda.energia.repositories.ClienteRepo;
import team5.azienda.energia.repositories.FatturaRepo;
import team5.azienda.energia.repositories.StatoFatturaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepo fatturaRepo;
    @Autowired
    StatoFatturaRepository statoFatturaRepository;
    @Autowired
    private ClienteRepo clienteRepo;

    //OK
    public Fattura saveFattura(FatturaDTO body) {
        this.fatturaRepo.findBynumero(body.numero()).ifPresent(
                user -> {
                    throw new BadRequestException("Il numero fattura scritto  " + body.numero() + " è già in uso!");
                }
        );
        StatoFattura st = new StatoFattura(body.statoFattura());
        statoFatturaRepository.save(st);
        Fattura newUser = new Fattura(
                body.dataFattura(),
                body.importo(),
                body.numero(),
                body.cliente(),
                st);

        return this.fatturaRepo.save(newUser);
    }

    //OK
    public Page<Fattura> findAllFatture(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepo.findAll(pageable);
    }

    //OK
    public Fattura findById(Long id) {
        return this.fatturaRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // TODO da provare dopo cliente ora non ho il database
    public List<Fattura> findbyCliente(Long id) {
        return this.fatturaRepo.findByClienteId(id);
    }

    //OK da vedere
    public Fattura findByIdupdateStatoFattura(long id, FatturaDTO body) {
        Fattura found = this.findById(id);
        StatoFattura st = new StatoFattura(body.statoFattura());
        statoFatturaRepository.save(st);// qua ci vorrebbe findbysstato fatture su stato fattureservice
        found.setStatoFattura(st);
        return this.fatturaRepo.save(found);
    }

    public List<Fattura> findFattureByStato(String statoFattura) {
        try {
            return fatturaRepo.findByStatoFatturaStato(statoFattura);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo per trovare le fatture in base all'importo
    public List<Fattura> findByImporto(double importo) {
        return fatturaRepo.findByImporto(importo);
    }

    public List<Fattura> findbyDataFattura(LocalDate data) {
        return this.fatturaRepo.findByDataFattura(data);
    }

    public void findByIdAndDelete(long id) {
        Fattura fattura = fatturaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        fatturaRepo.delete(fattura);
    }


}
