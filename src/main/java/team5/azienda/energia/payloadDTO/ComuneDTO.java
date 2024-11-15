package team5.azienda.energia.payloadDTO;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.*;
import team5.azienda.energia.entities.Provincia;

public record ComuneDTO(
        @NotNull(message = "Il codice provincia non può essere nullo")
        @Pattern(regexp = "\\d{3}", message= "Il codice provincia deve essere un numero di 3 cifre")
        String codProvincia,

        @NotNull(message = "Il codice comune non può essere nullo")
        @Pattern(regexp = "\\d{3}", message = "Il codice comune deve essssere un numero di 3 cifre")
        String codComune,

        @NotEmpty
        String nome,

        @NotEmpty
        String provincia

) {
}
