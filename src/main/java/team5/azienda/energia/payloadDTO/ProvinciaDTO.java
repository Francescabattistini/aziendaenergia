package team5.azienda.energia.payloadDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProvinciaDTO(
        @NotEmpty
        @Size(min=2, max = 6,message="La sigla deve essere di 2 caratteri")
        String sigla,
        @NotEmpty
        String nome,
        @NotEmpty
        @Size(min=3)
        String regione
) {
}
