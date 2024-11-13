package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Provincia {
    private String sigla;
    private String nome_provincia;
    private String regione;
    private int codice_provincia;

    public Provincia(String sigla, String nome_provincia, String regione) {
        this.sigla = sigla;
        this.nome_provincia = nome_provincia;
        this.regione = regione;
    }
}
