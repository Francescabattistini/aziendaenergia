package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record FatturaDTO(
        @NotNull(message = "La data della fattura non può essere nulla.")
        LocalDate dataFattura,

        @Positive(message = "L'importo deve essere positivo.")
        double importo,

        @Positive(message = "Il numero della fattura deve essere positivo.")
        int numero,

        @NotNull(message = "L'ID del cliente non può essere nullo.")
        Long clienteId,

        @NotNull(message = "Lo stato della fattura non può essere nullo.")
        @Size(min = 1, max = 50, message = "Lo stato della fattura deve essere tra 1 e 50 caratteri.")
        String statoFattura
) {
}
