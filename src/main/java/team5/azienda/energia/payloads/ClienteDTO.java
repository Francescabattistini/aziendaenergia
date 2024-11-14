package team5.azienda.energia.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClienteDTO(
        @NotNull(message = "la ragione sociale è obbligatoria!")
        String ragioneSociale,
        @NotNull(message = "l'email è obbligatoria!")
        @Email(message = "l'email inserita non è valida!!")
        @NotBlank(message = "L'email non può essere vuota")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "L'email deve contenere '@' e '.' in formato valido (esempio: esempio@dominio.com)"
        )
        String email,
        @NotNull(message = "la data di inserimento è obbligatoria!")
        LocalDate dataInserimento,
        @NotNull(message = "la data dell'ultimo contatto è obbligatoria!")
        LocalDate dataUltimoContatto,
        @NotNull(message = "il fatturato annuale è obbligatorio!")
        Double fatturatoAnnuale,
        @NotNull(message = "la pec è obbligatoria!")
        String pec,
        @NotNull(message = "il numero di telefono è obbligatorio!")
        @Size( max = 10, message = "il numero del telefono dev'essere di 10 caratteri ")
        @Pattern(
                regexp = "\\+?\\d{7,15}",
                message = "il telefono deve avere un numero valido e un prefisso internazionale!!"
                )
        Integer telefono,
        @NotNull(message = "l'email di contatto è obbligatorio!")
        @NotBlank(message = "L'email non può essere vuota")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "L'email deve contenere '@' e '.' in formato valido (esempio: esempio@dominio.com)"
        )
        String emailContatto,
        @NotNull(message = "il nome di contatto è obbligatorio!")
        @Size(min = 3, max = 15, message = "il nome deve essere almeno di 3 caratteri e massimo di 15")
        String nomeContatto,
        @NotNull(message = "il cognome di contatto è obbligatorio!")
        @Size(min = 3, max = 15, message = "il cognome deve essere almeno di 3 caratteri e massimo di 15")
        String cognomeContatto,
        @NotNull(message = "il telefono contatto è obbligatoria!")
        Integer telefonoContatto,
        String logoAziendale


) {

}
