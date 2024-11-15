package team5.azienda.energia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.entities.Fattura;
import team5.azienda.energia.entities.StatoFattura;
import team5.azienda.energia.payloadDTO.FatturaDTO;
import team5.azienda.energia.repositories.ClienteRepo;
import team5.azienda.energia.repositories.FatturaRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepo fatturaRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private StatoFatturaService statoFatturaService;

    public Fattura saveFattura(FatturaDTO fatturaDTO) {
        Cliente cliente = clienteRepo.findById(fatturaDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente non trovato con ID: " + fatturaDTO.clienteId()));

        StatoFattura statoFattura = statoFatturaService.findByStatoOrSaveNew(fatturaDTO.statoFattura());

        Fattura fattura = new Fattura();
        fattura.setDataFattura(fatturaDTO.dataFattura());
        fattura.setImporto(fatturaDTO.importo());
        fattura.setNumero(fatturaDTO.numero());
        fattura.setCliente(cliente);
        fattura.setStatoFattura(statoFattura);

        return fatturaRepo.save(fattura);
    }

    public FatturaDTO convertToDTO(Fattura fattura) {
        return new FatturaDTO(
                fattura.getDataFattura(),
                fattura.getImporto(),
                fattura.getNumero(),
                fattura.getCliente().getId(),
                fattura.getStatoFattura().getStato() // Assumendo che 'getStato' ritorni il nome dello stato
        );
    }

    public Page<Fattura> findAllFatture(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepo.findAll(pageable);
    }

    public Fattura findById(Long id) {
        return this.fatturaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fattura non trovata con ID: " + id));
    }

    public List<Fattura> findByCliente(Long clienteId) {
        return this.fatturaRepo.findByClienteId(clienteId);
    }

    public Fattura updateFattura(Long id, FatturaDTO body) {
        Fattura fattura = this.findById(id);
        StatoFattura statoFattura = statoFatturaService.findByStatoOrSaveNew(body.statoFattura());
        fattura.setStatoFattura(statoFattura);

        // Aggiorna altri campi se necessario
        fattura.setDataFattura(body.dataFattura());
        fattura.setImporto(body.importo());
        fattura.setNumero(body.numero());

        return this.fatturaRepo.save(fattura);
    }

    public List<Fattura> findFattureByStato(String statoFattura) {
        return fatturaRepo.findByStatoFatturaStato(statoFattura);
    }

    public List<Fattura> findByImporto(double importo) {
        return fatturaRepo.findByImporto(importo);
    }

    public List<Fattura> findByDataFattura(LocalDate data) {
        return this.fatturaRepo.findByDataFattura(data);
    }

    public void deleteFattura(long id) {
        Fattura fattura = fatturaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fattura non trovata con ID: " + id));
        fatturaRepo.delete(fattura);
    }
}
