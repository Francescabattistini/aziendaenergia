package team5.azienda.energia.servicies;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Comune;
import team5.azienda.energia.entities.Provincia;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.payloadDTO.ComuneDTO;
import team5.azienda.energia.repositories.ComuneRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepository cr;

    @Autowired
    private ProvinciaService ps;

    @Autowired
    private Validator validator;

    public Comune findByNome(String nome) {
        return this.cr.findByNome(nome).orElseThrow(() -> new NotFoundException("Comune " + nome + " non trovato"));
    }

    public Comune save(ComuneDTO body) {
        Provincia p = ps.findByNome(body.provincia());

        return this.cr.save(new Comune(body.codComune(), body.nome(), p));
    }

    public Page<Comune> findAll(int page, int size, String sortBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.cr.findAllAndProvincia(pageable);
    }


    public void estrazioneComuniCsv(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String linea;
        br.readLine();
        linea = br.readLine();
        String codiceProvinciaCorrente = "";
        int progressivo = 1;

        while (linea != null) {
            String[] colonne = linea.split(";");
            if (colonne.length < 4) {
                linea = br.readLine();
                continue;
            }

            String codProvincia = colonne[0].trim();
            String codComune = colonne[1].trim();
            String nomeComune = colonne[2].trim();
            String nomeProvincia = colonne[3].trim();

            if (!codProvincia.equals(codiceProvinciaCorrente)) {
                codiceProvinciaCorrente = codProvincia;
                progressivo = 1;
            }

            if (codComune.equals("#RIF!")) {
                if (progressivo < 10) {
                    codComune = "00" + progressivo;
                } else if (progressivo < 100) {
                    codComune = "0" + progressivo;
                }
            }

            progressivo++;

            if (!codProvincia.equals("Codice Provincia (Storico)(1)")){


                Provincia provincia = switch (nomeProvincia){
                    case "Verbano-Cusio-Ossola" -> ps.findByNome("Verbania");
                    case "Valle d'Aosta/Vallée d'Aoste" -> ps.findByNome("Aosta");
                    case "Monza e della Brianza" -> ps.findByNome("Monza-Brianza");
                    case "Bolzano/Bozen" -> ps.findByNome("Bolzano");
                    case "La Spezia" -> ps.findByNome("La-Spezia");
                    case "Reggio nell'Emilia" -> ps.findByNome("Reggio-Emilia");
                    case "Forlì-Cesena" -> ps.findByNome("Forli-Cesena");
                    case "Pesaro e Urbino" -> ps.findByNome("Pesaro-Urbino");
                    case "Ascoli Piceno" -> ps.findByNome("Ascoli-Piceno");
                    case "Vibo Valentia" -> ps.findByNome("Vibo-Valentia");
                    case "Reggio Calabria" -> ps.findByNome("Reggio-Calabria");
                    case "Sud Sardegna" -> ps.findByNome("Carbonia Iglesias");
                    default -> ps.findByNome(nomeProvincia);
                };

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!" + codProvincia + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                Comune c = new Comune(codComune,nomeComune,provincia);
                if (this.cr.findByNome(nomeComune).isEmpty())this.cr.save(c);

            if (!(Objects.equals(provincia.getCodiceProvincia(), codProvincia))) ps.findAndUpdate(provincia.getId(), codProvincia);
            c.setProvincia(provincia);
            this.cr.save(c);

            linea = br.readLine();}
        }
    }

}
