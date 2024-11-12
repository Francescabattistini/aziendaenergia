package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="comuni")
public class Comune {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;//TODO controlla CSV
    @Column(name = "nome_comune")
    private String nomeComune;

    @OneToMany(mappedBy = "comune")
    private List<Indirizzo> indirizzi;
    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;


    public Comune(String nomeComune) {
        this.nomeComune = nomeComune;
    }
}
