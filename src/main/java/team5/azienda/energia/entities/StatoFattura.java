package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "stato_fattura")
public class StatoFattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stato;

    @OneToMany(mappedBy = "statoFattura", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Fattura> fatture;

    public StatoFattura(String stato) {
        this.stato = stato;
    }
}
