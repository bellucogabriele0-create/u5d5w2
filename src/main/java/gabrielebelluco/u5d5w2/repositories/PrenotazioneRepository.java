package gabrielebelluco.u5d5w2.repositories;

import gabrielebelluco.u5d5w2.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> fndByPrenotazione(Prenotazione prenotazione);
}
