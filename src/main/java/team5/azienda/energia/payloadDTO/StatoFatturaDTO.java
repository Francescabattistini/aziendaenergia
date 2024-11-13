package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.NotBlank;

public record StatoFatturaDTO(
        @NotBlank(message = "Lo stato non può essere vuoto.")
        String stato
) {
}
