package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="fatture")
public class Fattura {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name="data_fattura")
    private LocalDate dataFattura;

    private double importo;

    private int numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @ToString.Exclude
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stato_fattura_id")
    @ToString.Exclude
    private StatoFattura statoFattura;



}
