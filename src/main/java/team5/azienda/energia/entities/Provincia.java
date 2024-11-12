package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Provincia {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;//TODO controlla CSV

    @Column(name = "nome_provincia")
    private String nome;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comune> comuni;

}
