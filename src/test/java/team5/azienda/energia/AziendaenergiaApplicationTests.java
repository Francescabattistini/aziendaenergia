package team5.azienda.energia;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team5.azienda.energia.entities.Provincia;
import team5.azienda.energia.services.ProvinciaService;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AziendaenergiaApplicationTests {
    @Autowired
    private ProvinciaService ps;

    @ParameterizedTest
    @CsvSource({"Erchie,Brindisi ", "Malalbergo, Bologna", " Vignola, Modena", "Bussolengo, Verona", "Rutigliano, Bari ", "Comacchio, Ferrara"})
    void Provinciamune(String nomeComune, String provincia) {
        Provincia p = ps.findByComune(nomeComune);
        String nomePrpvicnia = p.getNome();
        assertEquals(provincia, nomePrpvicnia);
    }

}
