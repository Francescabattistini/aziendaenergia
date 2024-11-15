package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.entities.Comune;
import team5.azienda.energia.entities.Provincia;
import team5.azienda.energia.services.ComuneService;
import team5.azienda.energia.services.ProvinciaService;

import java.io.IOException;

@RestController
public class ComuneController {
    @Autowired
    private ComuneService cs;
    @Autowired
    private ProvinciaService ps;

    @GetMapping("/comuni")
    public Page<Comune> findAllComuni(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size,
                                      @RequestParam(defaultValue = "nome") String sortBy) {
        return this.cs.findAll(page, size, sortBy);
    }

    @GetMapping("/province")
    public Page<Provincia> findAllProvince(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size,
                                           @RequestParam(defaultValue = "nome") String sortBy) {
        return this.ps.findAll(page, size, sortBy);
    }

    @PostMapping("/comuni")
    @ResponseStatus(HttpStatus.CREATED)
    public void popolate() throws IOException {
        String pathProvince = "src/main/resources/province-italiane.csv";
        String pathComuni = "src/main/resources/comuni-italiani.csv";
        ps.estrazioneProvinceCsv(pathProvince);
        cs.estrazioneComuniCsv(pathComuni);
    }

    @GetMapping("/{nomeComune}")
    public Provincia findProvincia(@PathVariable("nomeComune") String nomeComune) {
        return this.ps.findByComune(nomeComune);
    }
}
