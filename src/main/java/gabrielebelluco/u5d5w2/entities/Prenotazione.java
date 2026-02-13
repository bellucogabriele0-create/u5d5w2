package gabrielebelluco.u5d5w2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "prenotazioniId")
    @Setter(AccessLevel.NONE)
    private UUID PrenotazioniId;
    @Column(nullable = false)
    private LocalDate dataRichiesta;
    @Column(nullable = false)
    private String notePreferenze;
    @Column
    private Boolean StatoViaggio = null;
    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    public Prenotazione(LocalDate dataRichiesta, String notePreferenze, Viaggio viaggio, Dipendente dipendente) {
        this.dataRichiesta = dataRichiesta;
        this.notePreferenze = notePreferenze;
        this.viaggio = viaggio;
        this.dipendente = dipendente;
    }
}
