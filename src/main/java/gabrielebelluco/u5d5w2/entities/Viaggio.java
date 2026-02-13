package gabrielebelluco.u5d5w2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    @Column(name = "Id_viaggi")
    private UUID Id;
    @Column(nullable = false)
    private String destinazione;
    @Column(nullable = false)
    private LocalDate data;
    @Column
    private boolean completato;

    public Viaggio(boolean completato, LocalDate data, String destinazione) {
        this.completato = completato;
        this.data = data;
        this.destinazione = destinazione;
    }
}
