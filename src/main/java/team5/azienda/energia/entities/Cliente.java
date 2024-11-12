package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;
import team5.azienda.energia.enums.TypeSedeLegale;

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
    private String email;
    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;
    @Column(name = "data_ultimo_contatto")
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
    @Enumerated(EnumType.STRING)
    private TypeSedeLegale typesedelegale;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @OneToMany(mappedBy = "cliente")
    private List <Fattura> fatture;

    @ManyToOne
    @JoinColumn(name = "inidirizzo_id")
    private Indirizzo indirizzo;


    public Cliente(String ragioneSociale, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto,
                   double fatturatoAnnuale, String pec, int telefono, String emailContatto,
                   String nomeContatto, String cognomeContatto, int telefonoContatto, String logoAziendale) {
        this.ragioneSociale = ragioneSociale;
        this.email = email;
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
