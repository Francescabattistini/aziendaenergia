package team5.azienda.energia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import team5.azienda.energia.entities.Comune;
import team5.azienda.energia.servicies.ComuneService;
import team5.azienda.energia.servicies.ProvinciaService;

import java.io.IOException;

@RestController
@RequestMapping("/comuni")
public class ComuneController {
    @Autowired
    private ComuneService cs;
    @Autowired
    private ProvinciaService ps;

    @GetMapping
    public Page<Comune> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size,
                                @RequestParam(defaultValue = "nome") String sortBy) {
        return this.cs.findAll(page, size, sortBy);
    }

    @PostMapping
    public void popolate() throws IOException {
        String pathProvince = "src/main/resources/province-italiane.csv";
        String pathComuni = "src/main/resources/comuni-italiani.csv";
        ps.estrazioneProvinceCsv(pathProvince);
        cs.estrazioneComuniCsv(pathComuni);
    }
}
