package team5.azienda.energia.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.entities.StatoFattura;

import java.time.LocalDate;

public record FatturaDTO(
        @NotNull(message = "ila data della fattura è obbligatoria!")
        LocalDate dataFattura,
        @NotNull(message = "l'importo è obbligatorio!")
        Double importo,
        @NotNull(message = "il numero è obbligatorio!")
        Integer numero,
        @NotNull(message = "il nome del cliente è obbligatorio!")
        @Size(min = 3, max = 15, message = "il nome deve essere almeno di 3 caratteri e massimo di 15")
        Cliente cliente,
        @NotNull(message = "lo stato della fattura è obbligatorio!")
        StatoFattura statoFattura
) {
}
