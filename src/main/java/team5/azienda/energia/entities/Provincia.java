package team5.azienda.energia.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String nome;

    private String sigla;

    private String codiceProvincia;

    private String regione;

    @JsonIgnore
    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni = new ArrayList<>();

    public Provincia(String nome, String sigla, String regione) {
        this.nome = nome;
        this.sigla = sigla;
        this.regione =regione;
    }

    public void setCodiceProvincia(String codiceProvincia) {
        this.codiceProvincia = codiceProvincia;
    }
}
