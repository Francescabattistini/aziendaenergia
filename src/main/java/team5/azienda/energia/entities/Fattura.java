package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "fatture")
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id; // Cambiato da 'long' a 'Long'

    @Column(name = "data_fattura", nullable = false)
    private LocalDate dataFattura;

    @Column(nullable = false)
    private double importo;

    @Column(nullable = false)
    private int numero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @ToString.Exclude
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stato_fattura_id", nullable = false)
    @ToString.Exclude
    private StatoFattura statoFattura;

    public Fattura(LocalDate dataFattura, double importo, int numero, Cliente cliente, StatoFattura statoFattura) {
        this.dataFattura = dataFattura;
        this.importo = importo;
        this.numero = numero;
        this.cliente = cliente;
        this.statoFattura = statoFattura;
    }
}
