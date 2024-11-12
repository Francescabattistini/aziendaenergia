package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class StatoFattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stato;

    @OneToMany(mappedBy = "statoFattura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fattura> fatture;

    public StatoFattura(String stato, List<Fattura> fatture) {
        this.stato = stato;
        this.fatture = fatture;
    }

    public List<Fattura> getFatture() {
        return fatture;
    }
}
