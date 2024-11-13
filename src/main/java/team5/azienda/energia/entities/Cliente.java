package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "clienti")
public class Cliente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;

    @Column(name = "data_ultimo_contatto", updatable = false)
    @CreationTimestamp
    private LocalDate dataUltimoContatto;

    @Column(name = "fatturato_annuale")
    private double fatturatoAnnuale;

    private String pec;

    private int telefono;

    @Column(name = "email_contatto")
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "telefono_contatto")
    private int telefonoContatto;

    @Column(name = "logo_aziendale")
    private String logoAziendale;// TODO modifica per fare il placeholder vedi se in service o nel costruttore


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Indirizzo> indirizzi;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Fattura> fatture;

    public Cliente(String ragioneSociale, LocalDate dataInserimento, LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, int telefono, String emailContatto, String nomeContatto, String cognomeContatto, int telefonoContatto, String logoAziendale, List<Indirizzo> indirizzi, List<Fattura> fatture) {
        this.ragioneSociale = ragioneSociale;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
        this.indirizzi = indirizzi;
        this.fatture = fatture;
    }

    public Cliente(String ragioneSociale, LocalDate dataInserimento, LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, int telefono, String emailContatto, String nomeContatto, String cognomeContatto, int telefonoContatto, String logoAziendale) {
        this.ragioneSociale = ragioneSociale;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
    }
}
