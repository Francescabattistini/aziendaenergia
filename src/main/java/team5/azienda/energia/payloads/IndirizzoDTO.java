package team5.azienda.energia.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.entities.Comune;

public record IndirizzoDTO(
        @NotNull(message = "la via è obbligatoria")
        String via,
        @NotNull(message = "il numero civico è obbligatorio")
        Integer civico,
        @NotNull(message = "il nome del cliente è obbligatorio")
        @Size(min = 3, max = 15, message = "il nome deve essere almeno di 3 caratteri e massimo di 15")
        Cliente cliente,
        @NotNull(message = "il nome del comune è obbligatorio!")
        @Size(min = 3, max = 15, message = "il nome deve essere almeno di 3 caratteri e massimo di 15")
        Comune comune
) {
}
