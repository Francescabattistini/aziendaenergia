package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    private String via;

    private int civico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @ToString.Exclude
    private Cliente cliente;
    @Embedded
    private Comune comune;

    public Indirizzo(String via, int civico, Cliente cliente, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.cliente = cliente;
        this.comune = comune;
    }
}

