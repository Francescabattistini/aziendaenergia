package team5.azienda.energia.payloadDTO;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import team5.azienda.energia.entities.Provincia;

public record ComuneDTO(
        @Positive(message = "Il progressivo comune deve essere un numero positivo.")
        int progressivo_comune,

        @NotBlank(message = "Il nome del comune non può essere vuoto.")
        String nome_comune,

        @NotNull(message = "La provincia non può essere nulla.")
        @Embedded
        Provincia provincia


) {
}
