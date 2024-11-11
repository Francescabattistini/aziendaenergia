package team5.aziendaenergia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String via;
    private int civico;
    private String località;
    private int cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provicia;

    @OneToMany(mappedBy = "indirizzo")
    private List<Cliente> clienti;

    public Indirizzo(String via, int civico, String località, int cap) {
        this.via = via;
        this.civico = civico;
        this.località = località;
        this.cap = cap;
    }
}
