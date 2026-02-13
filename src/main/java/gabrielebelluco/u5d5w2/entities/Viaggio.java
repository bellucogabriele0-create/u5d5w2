package gabrielebelluco.u5d5w2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "Id_viaggi")
    private Long viaggiId;
    @Column(nullable = false)
    private String destinazione;
    @Column(nullable = false)
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;

    public Viaggio(StatoViaggio stato, LocalDate data, String destinazione) {
        this.stato = stato;
        this.data = data;
        this.destinazione = destinazione;
    }
}
