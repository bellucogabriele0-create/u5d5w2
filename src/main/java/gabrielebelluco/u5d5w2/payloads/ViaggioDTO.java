package gabrielebelluco.u5d5w2.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "il viaggio deve avere una destinazione")
        @Size(max = 100, min = 1, message = "il viaggio deve avere una destinazione di massimo 100 caratteri")
        String destinazione,
        @NotBlank(message = "")
        LocalDate data,
        boolean completato

) {
    public String getStato() {
        return this.completato ? "COMPLETATO" : "IN_PROGRAMMA";
    }

}
