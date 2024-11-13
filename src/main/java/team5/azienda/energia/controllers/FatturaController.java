package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.entities.Fattura;
import team5.azienda.energia.exceptions.BadRequestException;
import team5.azienda.energia.payloadDTO.FatturaDTO;
import team5.azienda.energia.servicies.FatturaService;
import team5.azienda.energia.servicies.StatoFatturaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/fattures")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;
    @Autowired
    private StatoFatturaService statoFatturaService;

    @GetMapping
    public Page<Fattura> findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {

        return this.fatturaService.findAllFatture(page, size, sortBy);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Fattura save(@RequestBody @Validated FatturaDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.fatturaService.saveFattura(body);
    }


    @GetMapping("/{userId}")
    public Fattura findById(@PathVariable Long userId) {
        return this.fatturaService.findById(userId);
    }

    @GetMapping("/{id_cliente}/fatture")
    public List<Fattura> findByCliente(@PathVariable Long id) {
        return this.fatturaService.findbyCliente(id);
    }

    @PutMapping("/{fatturaId}")
    public Fattura findByIdAndUpdate(@PathVariable Long fatturaId, @RequestBody @Validated FatturaDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        return this.fatturaService.findByIdupdateStatoFattura(fatturaId, body);
    }


    @GetMapping("/data")
    public List<Fattura> findByDataFattura(@RequestParam LocalDate data) {
        return fatturaService.findbyDataFattura(data);
    }

    //Get http://localhost:3001/fattures/stato/PAGATA
    @GetMapping("/stato/{statoFatturaId}")
    public List<Fattura> findFattureByStato(@PathVariable String statoFattura) {

        return fatturaService.findFattureByStato(statoFattura);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long userId) {
        this.fatturaService.findByIdAndDelete(userId);
    }


}
