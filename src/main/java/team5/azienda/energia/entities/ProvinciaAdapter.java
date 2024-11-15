package team5.azienda.energia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class ProvinciaAdapter {
    private String nome;

    private String sigla;

    private String codiceProvincia;

    private String regione;

    private List<Comune> comuni ;

    public ProvinciaAdapter(Provincia provincia) {
        this.nome = provincia.getNome();
        this.sigla = provincia.getSigla();
        this.regione = provincia.getRegione();
        this.comuni = provincia.getComuni();
    }

}
