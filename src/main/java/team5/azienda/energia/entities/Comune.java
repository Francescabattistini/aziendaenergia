package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "comuni")
public class Comune {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Setter(AccessLevel.NONE)
   private long id;
   private String codiceComune;

   private String nome;

   @ManyToOne
   @JoinColumn(name = "provincia_id")
   private Provincia provincia;

   @OneToMany(mappedBy = "comune")
   private List<Indirizzo> indirizzi;

   public Comune( String codiceComune, String nome, Provincia provincia) {
      this.codiceComune = codiceComune;
      this.nome = nome;
      this.provincia = provincia;
   }

}
