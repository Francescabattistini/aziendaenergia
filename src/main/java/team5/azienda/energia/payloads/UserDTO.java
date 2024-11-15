package team5.azienda.energia.payloads;

import jakarta.validation.constraints.*;

public record UserDTO(
        @NotNull(message = "l'username è obbligatorio!")
        @Size( min=3, max=15, message = "il numero di caratteri dell'username dev'essere tra i 3 e i 15 caratteri ")
        @Pattern(
                regexp = "\\+?\\d{7,15}",
                message = "l'username deve caratteri validi"
        )
        String username,
        @NotNull(message = "il ruolo è obbligatorio!")
        @Email(message = "l'email inserita non è valida!!")
        @NotBlank(message = "L'email non può essere vuota")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "L'email deve contenere '@' e '.' in formato valido (esempio: esempio@dominio.com)"
        )
        String email,
        @NotNull(message = "la password è obbligatoria!")
        @Size( min=8, message = "la password deve avere almeno 8 caratteri!! ")
        @Pattern(
                regexp = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$",
                message = "La password deve contenere almeno una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale"
        )
        String password,
        @NotNull(message = "il nome è obbligatorio!")
        @Size( min=3, max=15, message = "il numero di caratteri dell'username dev'essere tra i 3 e i 15 caratteri ")
        String nome,
        @NotNull(message = "il cognome è obbligatorio!")
        @Size( min=3, max=15, message = "il numero di caratteri dell'username dev'essere tra i 3 e i 15 caratteri ")
        String cognome,
        @NotNull(message = "l'avatar è obbligatorio!")
        String avatar
) {
}
