package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comune_id")
    private Comune comune;



}

