package gabrielebelluco.u5d5w2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank(message = "il dipendente deve avere un username")
        @Size(min = 5, max = 15, message = "l'username deve essere dai 5 a 15 caratteri")
        String username,
        @NotBlank(message = "il dipendente deve avere un nome")
        @Size(min = 5, max = 15, message = "il nome deve essere dai 5 a 15 caratteri")
        String nome,
        @NotBlank(message = "il dipendente deve avere un cognome")
        @Size(min = 5, max = 15, message = "il cognome deve essere dai 5 a 15 caratteri")
        String cognome,
        @NotBlank(message = "il dipendente deve avere un Email")
        @Email(message = "l'email non è valida ")
        String email,
        @NotBlank(message = "il dipendente deve avere un immagine")
        String immagineDip

) {
}
