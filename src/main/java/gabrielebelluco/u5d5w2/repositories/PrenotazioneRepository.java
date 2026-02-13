package gabrielebelluco.u5d5w2.repositories;

import gabrielebelluco.u5d5w2.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    List<Prenotazione> findByDipendenteDipendenteIdAndDataRichiesta(UUID dipendenteId, LocalDate dataRichiesta);

    List<Prenotazione> findByViaggioId(UUID viaggioId);
}
