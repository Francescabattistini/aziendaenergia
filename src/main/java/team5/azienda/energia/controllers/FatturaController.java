package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.entities.Fattura;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.payloads.FatturaDTO;
import team5.azienda.energia.services.FatturaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    // GET http://localhost:8080/fatture?page=0&size=10&sortBy=id
    @GetMapping
    public Page<FatturaDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        Page<Fattura> fatturaPage = this.fatturaService.findAllFatture(page, size, sortBy);
        return fatturaPage.map(fatturaService::convertToDTO);
    }

    // POST http://localhost:8080/fatture
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FatturaDTO save(@RequestBody @Validated FatturaDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        Fattura savedFattura = this.fatturaService.saveFattura(body);
        return this.fatturaService.convertToDTO(savedFattura);
    }

    // GET http://localhost:8080/fatture/{fatturaId}
    @GetMapping("/{fatturaId}")
    public FatturaDTO findById(@PathVariable Long fatturaId) {
        Fattura fattura = this.fatturaService.findById(fatturaId);
        return this.fatturaService.convertToDTO(fattura);
    }

    // GET http://localhost:8080/fatture/cliente/{clienteId}/fatture
    @GetMapping("/cliente/{clienteId}/fatture")
    public List<FatturaDTO> findByCliente(@PathVariable Long clienteId) {
        List<Fattura> fatture = this.fatturaService.findByCliente(clienteId);
        return fatture.stream()
                .map(fatturaService::convertToDTO)
                .collect(Collectors.toList());
    }

    // PUT http://localhost:8080/fatture/{fatturaId}
    @PutMapping("/{fatturaId}")
    public FatturaDTO updateFattura(@PathVariable Long fatturaId,
                                    @RequestBody @Validated FatturaDTO body,
                                    BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        Fattura updatedFattura = this.fatturaService.updateFattura(fatturaId, body);
        return this.fatturaService.convertToDTO(updatedFattura);
    }

    // GET http://localhost:8080/fatture/data?data=2024-04-27
    @GetMapping("/data")
    public List<FatturaDTO> findByDataFattura(@RequestParam LocalDate data) {
        List<Fattura> fatture = fatturaService.findByDataFattura(data);
        return fatture.stream()
                .map(fatturaService::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET http://localhost:8080/fatture/stato/PAGATA
    @GetMapping("/stato/{statoFattura}")
    public List<FatturaDTO> findFattureByStato(@PathVariable String statoFattura) {
        List<Fattura> fatture = fatturaService.findFattureByStato(statoFattura);
        return fatture.stream()
                .map(fatturaService::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET http://localhost:8080/fatture/importo?importo=100.50
    @GetMapping("/importo")
    public List<FatturaDTO> getFattureByImporto(@RequestParam double importo) {
        List<Fattura> fatture = fatturaService.findByImporto(importo);
        return fatture.stream()
                .map(fatturaService::convertToDTO)
                .collect(Collectors.toList());
    }

    // DELETE http://localhost:8080/fatture/{fatturaId}
    @DeleteMapping("/{fatturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFattura(@PathVariable Long fatturaId) {
        this.fatturaService.deleteFattura(fatturaId);
    }
}
