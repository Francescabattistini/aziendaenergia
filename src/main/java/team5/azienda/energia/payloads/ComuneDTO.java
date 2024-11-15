package team5.azienda.energia.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import team5.azienda.energia.entities.Provincia;

public record ComuneDTO(
        @NotNull(message = "il progressivo comune è obbligatorio!")
        String progressivoComune,
        @NotNull(message = "il nome del comune è obbligatorio!")
        @Size(min = 3, max = 15, message = "il nome deve essere almeno di 3 caratteri e massimo di 15")
        String nomeComune,
        @NotNull(message = "la Provincia è obbligatoria!")
        Provincia provincia
) {
}
