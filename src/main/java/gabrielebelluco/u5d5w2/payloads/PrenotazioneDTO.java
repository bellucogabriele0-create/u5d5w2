package gabrielebelluco.u5d5w2.payloads;

import gabrielebelluco.u5d5w2.entities.Dipendente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PrenotazioneDTO(
        @NotBlank(message = "la prenotazione deve avere un viaggio")
        Long viaggio,
        @NotBlank(message = "la prenotazione deve avere una data richiesta")
        LocalDate dataRichiesta,
        @NotBlank(message = "la prenotazione deve avere un viaggio")
        Dipendente dipendente,
        @Size(max = 5000, message = "la prenotazione può avere un delle note")
        String notePreferenze

) {
}
