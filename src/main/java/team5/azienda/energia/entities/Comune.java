package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Comune {
   private int progressivo_comune;
   private String nome_comune;
   @Embedded
   private Provincia provincia;
}
