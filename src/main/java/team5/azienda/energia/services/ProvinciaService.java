package team5.azienda.energia.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.Comune;
import team5.azienda.energia.entities.Provincia;
import team5.azienda.energia.exceptions.NotFoundException;
import team5.azienda.energia.payloadDTO.ProvinciaDTO;
import team5.azienda.energia.repositories.ComuneRepository;
import team5.azienda.energia.repositories.ProvinciaRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository pr;

    @Autowired
    private ComuneRepository cr;

    @Autowired
    private Validator validator;


    public Provincia save(ProvinciaDTO body) {
        Provincia newProvince = new Provincia(body.nome(), body.sigla(), body.regione());
        return this.pr.save(newProvince);
    }


    public Provincia findByComune(String nomeComune) {
        Comune found = this.cr.findByNome(nomeComune).orElseThrow(() -> new NotFoundException("Provincia non trovata"));
        return this.pr.findByComuniContaining(found).orElseThrow(() -> new NotFoundException("Provincia non trovata"));
    }

    public Optional<Provincia> findBySigla(String sigla) {
        if (sigla == null || sigla.isEmpty()) {
            System.out.println("La sigla non puo essere vuota");
            throw new IllegalArgumentException("La sigla non puo essere vuota");
        }
        Optional<Provincia> provincia = pr.findBySigla(sigla);
        if (provincia.isEmpty()) {
            System.out.println("Not found");
            throw new NotFoundException("Provincia non trovata");
        }
        return provincia;
    }

    public Provincia findAndUpdate(long id, String cod) {
        Optional<Provincia> provincia = pr.findById(id);
        provincia.get().setCodiceProvincia(cod);
        if (provincia.isPresent()) return this.pr.save(provincia.get());
        return null;
    }


    public Provincia findByNome(String nome) {
        System.out.println(nome);
        return pr.findByNomeLike(nome).orElseThrow(() -> new NotFoundException("Provincia " + nome + " non trovata"));
    }


    public void estrazioneProvinceCsv(String path) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linea;
            br.readLine();
            linea = br.readLine();
            while (linea != null) {
                String[] colonne = linea.split(";");
                if (colonne.length < 3) {
                    System.out.println(("Riga non valida"));
                    continue;
                }
                String sigla = colonne[0].trim();
                String nome = colonne[1].trim();
                String regione = colonne[2].trim();

                ProvinciaDTO provincia = new ProvinciaDTO(sigla, nome, regione);
                Set<ConstraintViolation<ProvinciaDTO>> violations = validator.validate(provincia);
                if (!violations.isEmpty()) {
                    continue;
                }
                if (this.pr.findByNome(nome).isEmpty()) this.save(provincia);

                linea = br.readLine();
            }
        } catch (Exception e) {
            new Exception(String.valueOf(e.getCause()));
        }

    }

    public Page<Provincia> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        return this.pr.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }
}
