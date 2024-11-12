package team5.azienda.energia.payloads;

import jakarta.validation.constraints.NotNull;

public record StatoFatturaDTO(
        @NotNull(message = "il ruolo è obbligatorio!")
        String ruolo
) {
}
