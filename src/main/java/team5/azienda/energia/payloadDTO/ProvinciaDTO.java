package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProvinciaDTO(
        @NotBlank(message = "La sigla della provincia non può essere vuota.")
        @Size(max = 2, message = "La sigla della provincia deve avere massimo 2 caratteri.")
        String sigla,

        @NotBlank(message = "Il nome della provincia non può essere vuoto.")
        String nome_provincia,

        @NotBlank(message = "La regione non può essere vuota.")
        String regione,

        @Positive(message = "Il codice della provincia deve essere un numero positivo.")
        int codice_provincia
) {
}
