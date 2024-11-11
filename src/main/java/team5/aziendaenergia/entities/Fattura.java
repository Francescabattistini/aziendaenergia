package team5.aziendaenergia.entities;

import jakarta.persistence.*;
import lombok.*;
import team5.aziendaenergia.enums.TypeStato;

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
    @Enumerated(EnumType.STRING)
    private TypeStato typestato;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Fattura(LocalDate dataFattura, double importo, int numero, TypeStato typestato) {
        this.dataFattura = dataFattura;
        this.importo = importo;
        this.numero = numero;
        this.typestato = typestato;
    }
}
