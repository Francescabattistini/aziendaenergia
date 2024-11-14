package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.entities.Comune;
import team5.azienda.energia.entities.Provincia;
import team5.azienda.energia.servicies.ComuneService;
import team5.azienda.energia.servicies.ProvinciaService;

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
    public String findAllProvince(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size,
                                           @RequestParam(defaultValue = "nome") String sortBy) {
        return this.ps.findAll(page, size, sortBy).forEach(p -> p.getComunes());
    }

    @PostMapping("/comuni")
    @ResponseStatus(HttpStatus.CREATED)
    public void popolate() throws IOException {
        String pathProvince = "src/main/resources/province-italiane.csv";
        String pathComuni = "src/main/resources/comuni-italiani.csv";
        ps.estrazioneProvinceCsv(pathProvince);
        cs.estrazioneComuniCsv(pathComuni);
    }
}