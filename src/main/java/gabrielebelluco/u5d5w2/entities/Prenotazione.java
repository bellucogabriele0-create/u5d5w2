package gabrielebelluco.u5d5w2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prenotazioniId")
    @Setter(AccessLevel.NONE)
    private Long PrenotazioniId;
    @Column(nullable = false)
    private LocalDate dataRichiesta;
    @Column(nullable = false)
    private String notePreferenze;
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
