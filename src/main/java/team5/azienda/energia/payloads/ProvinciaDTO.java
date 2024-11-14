package team5.azienda.energia.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProvinciaDTO(
        @NotNull(message = "la sigla è obbligatoria!")
        String sigla,
        @NotNull(message = "il nome della provincia è obbligatorio!")
        @Size(min = 3, max = 15, message = "il nome deve essere almeno di 3 caratteri e massimo di 15")
        String nome_provincia,
        @NotNull(message = "la regione è obbligatoria!")
        String regione,
        @NotNull(message = "il codice provincia è obbligatorio!")
        Integer codice_provincia
) {
}
