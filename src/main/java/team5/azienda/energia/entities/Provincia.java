package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="province")
public class Provincia {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;//TODO controlla CSV
    @Column(name = "nome_provincia")
    private String nomeprovincia;


    @OneToMany(mappedBy = "indirizzo")
    private List<Indirizzo> indirizzi;
    @OneToMany(mappedBy = "comune")
    private List<Comune> comuni;

    public Provincia(String nomeprovincia, List<Indirizzo> indirizzi) {
        this.nomeprovincia = nomeprovincia;
        this.indirizzi = indirizzi;
    }
}
