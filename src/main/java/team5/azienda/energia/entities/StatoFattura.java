package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter //questo perchè non c'era?
@Getter
@NoArgsConstructor
@Table(name = "stato_fatture")
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stato;

    @OneToMany(mappedBy = "statoFattura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fattura> fatture;

    public StatoFattura(String stato) {
        this.stato = stato;
    }


}
