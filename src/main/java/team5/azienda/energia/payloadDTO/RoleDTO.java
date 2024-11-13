package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(

        @NotBlank(message = "Il ruolo non può essere vuoto.")
        String ruolo

) {
}
