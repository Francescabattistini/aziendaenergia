package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.entities.StatoFattura;

import java.time.LocalDate;

public record FatturaDTO(
        @NotNull(message = "La data della fattura non può essere nulla.")
        LocalDate dataFattura,

        @Positive(message = "L'importo deve essere un numero positivo.")
        double importo,

        @Positive(message = "Il numero della fattura deve essere positivo.")
        int numero,

        @NotNull(message = "Il cliente non può essere nullo.")
        Cliente cliente,

        @NotNull(message = "Lo stato della fattura non può essere nullo.")
        StatoFattura statoFattura
) {
}
