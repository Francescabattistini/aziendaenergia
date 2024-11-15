package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UserDTO(
        @NotBlank(message = "Il nome utente non può essere vuoto.")
        String username,

        @Email(message = "L'email deve essere un'email valida.")
        @NotBlank(message = "L'email non può essere vuota.")
        String email,

        String password,

        @NotBlank(message = "Il nome non può essere vuoto.")
        String nome,

        @NotBlank(message = "Il cognome non può essere vuoto.")
        String cognome,

        String avatar
) {
}
