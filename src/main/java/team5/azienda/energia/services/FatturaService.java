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
import team5.azienda.energia.exceptions.NotFoundException;
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

    /**
     * Salva una nuova Fattura basata sul FatturaDTO.
     *
     * @param fatturaDTO Il DTO contenente i dati della fattura.
     * @return La fattura salvata.
     */
    public Fattura saveFattura(FatturaDTO fatturaDTO) {
        Cliente cliente = clienteRepo.findById(fatturaDTO.clienteId())
                .orElseThrow(() -> new NotFoundException("Cliente non trovato con ID: " + fatturaDTO.clienteId()));

        StatoFattura statoFattura = statoFatturaService.findByStatoOrSaveNew(fatturaDTO.statoFattura());

        Fattura fattura = new Fattura();
        fattura.setDataFattura(fatturaDTO.dataFattura());
        fattura.setImporto(fatturaDTO.importo());
        fattura.setNumero(fatturaDTO.numero());
        fattura.setCliente(cliente);
        fattura.setStatoFattura(statoFattura);

        return fatturaRepo.save(fattura);
    }

    /**
     * Converte una Fattura in FatturaDTO.
     *
     * @param fattura La fattura da convertire.
     * @return Il DTO corrispondente.
     */
    public FatturaDTO convertToDTO(Fattura fattura) {
        return new FatturaDTO(
                fattura.getDataFattura(),
                fattura.getImporto(),
                fattura.getNumero(),
                fattura.getCliente().getId(),
                fattura.getStatoFattura().getStato() // Assumendo che 'getStato' ritorni il nome dello stato
        );
    }

    /**
     * Recupera tutte le fatture con paginazione e ordinamento.
     *
     * @param page   Il numero della pagina.
     * @param size   La dimensione della pagina.
     * @param sortBy Il campo su cui ordinare.
     * @return Una pagina di fatture.
     */
    public Page<Fattura> findAllFatture(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepo.findAll(pageable);
    }

    /**
     * Trova una fattura per ID.
     *
     * @param id L'ID della fattura.
     * @return La fattura trovata.
     */
    public Fattura findById(Long id) {
        return this.fatturaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata con ID: " + id));
    }

    /**
     * Trova tutte le fatture associate a un cliente.
     *
     * @param clienteId L'ID del cliente.
     * @return Una lista di fatture.
     */
    public List<Fattura> findByCliente(Long clienteId) {
        return this.fatturaRepo.findByClienteId(clienteId);
    }

    /**
     * Aggiorna lo stato di una fattura.
     *
     * @param id   L'ID della fattura da aggiornare.
     * @param body Il DTO contenente i nuovi dati.
     * @return La fattura aggiornata.
     */
    public Fattura updateFattura(Long id, FatturaDTO body) {
        Fattura fattura = this.findById(id);
        StatoFattura statoFattura = statoFatturaService.findByStatoOrSaveNew(body.statoFattura());
        fattura.setStatoFattura(statoFattura);

        // Aggiornamento di altri campi se necessario
        fattura.setDataFattura(body.dataFattura());
        fattura.setImporto(body.importo());
        fattura.setNumero(body.numero());

        return this.fatturaRepo.save(fattura);
    }

    /**
     * Trova tutte le fatture con uno stato specifico.
     *
     * @param statoFattura Il nome dello stato della fattura.
     * @return Una lista di fatture.
     */
    public List<Fattura> findFattureByStato(String statoFattura) {
        return fatturaRepo.findByStatoFatturaStato(statoFattura);
    }

    /**
     * Trova tutte le fatture con un importo specifico.
     *
     * @param importo L'importo da cercare.
     * @return Una lista di fatture.
     */
    public List<Fattura> findByImporto(double importo) {
        return fatturaRepo.findByImporto(importo);
    }

    /**
     * Trova tutte le fatture con una data specifica.
     *
     * @param data La data della fattura.
     * @return Una lista di fatture.
     */
    public List<Fattura> findByDataFattura(LocalDate data) {
        return this.fatturaRepo.findByDataFattura(data);
    }

    /**
     * Elimina una fattura per ID.
     *
     * @param id L'ID della fattura da eliminare.
     */
    public void deleteFattura(long id) {
        Fattura fattura = fatturaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Fattura non trovata con ID: " + id));
        fatturaRepo.delete(fattura);
    }
}
