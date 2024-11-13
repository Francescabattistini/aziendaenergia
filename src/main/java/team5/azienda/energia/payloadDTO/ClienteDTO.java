package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClienteDTO(
        @NotBlank(message = "La ragione sociale non può essere vuota.")
        @Size(max = 100, message = "La ragione sociale deve avere massimo 100 caratteri.")
        String ragioneSociale,

        @NotNull(message = "La data di inserimento non può essere nulla.")
        LocalDate dataInserimento,

        @NotNull(message = "La data dell'ultimo contatto non può essere nulla.")
        LocalDate dataUltimoContatto,
        @Positive(message = "Il fatturato annuale deve essere un numero positivo.")
        double fatturatoAnnuale,

        @NotBlank(message = "L'indirizzo PEC non può essere vuoto.")
        @Email(message = "L'indirizzo PEC deve essere un'email valida.")
        String pec,

        int telefono,

        @NotBlank(message = "L'email di contatto non può essere vuota.")
        @Email(message = "L'email di contatto deve essere un'email valida.")
        String emailContatto,

        @NotBlank(message = "Il nome del contatto non può essere vuoto.")
        @Size(max = 50, message = "Il nome del contatto deve avere massimo 50 caratteri.")
        String nomeContatto,

        @NotBlank(message = "Il cognome del contatto non può essere vuoto.")
        @Size(max = 50, message = "Il cognome del contatto deve avere massimo 50 caratteri.")
        String cognomeContatto,

        int telefonoContatto,

        String logoAziendale

) {
}
