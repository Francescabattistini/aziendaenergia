package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import team5.azienda.energia.entities.Cliente;
import team5.azienda.energia.entities.Comune;

public record IndirizzoDTO(

        @NotBlank(message = "La via non può essere vuota.")
        String via,

        @Positive(message = "Il numero civico deve essere positivo.")
        int civico,

        @NotNull(message = "Il cliente non può essere nullo.")
        Cliente cliente,

        @NotNull(message = "Il comune non può essere nullo.")
        Comune comune
) {
}
