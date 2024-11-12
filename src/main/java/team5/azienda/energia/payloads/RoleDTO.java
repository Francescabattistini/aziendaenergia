package team5.azienda.energia.payloads;

import jakarta.validation.constraints.NotNull;

public record RoleDTO(
        @NotNull(message = "la via è obbligatoria")
        String ruolo
) {
}
