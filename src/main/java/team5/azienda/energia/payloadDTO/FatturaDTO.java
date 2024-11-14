package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.NotNull;
import team5.azienda.energia.entities.Cliente;

import java.time.LocalDate;

public record FatturaDTO(
        @NotNull(message = "La data della fattura non può essere nulla.")
        LocalDate dataFattura,

        double importo,

        int numero,

        // @NotNull(message = "Il cliente non può essere nullo.")
        Cliente cliente,

        // @NotNull(message = "Lo stato della fattura non può essere nullo.")
        String statoFattura
) {
}
