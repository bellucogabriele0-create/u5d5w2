package gabrielebelluco.u5d5w2.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotBlank(message = "la prenotazione deve avere un viaggio")
        UUID viaggioid,
        @NotBlank(message = "la prenotazione deve avere una data richiesta")
        LocalDate dataRichiesta,
        @NotBlank(message = "la prenotazione deve avere un viaggio")
        UUID dipendenteid,
        @Size(max = 5000, message = "la prenotazione può avere un delle note")
        String notePreferenze

) {
}
