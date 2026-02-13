package gabrielebelluco.u5d5w2.repositories;

import gabrielebelluco.u5d5w2.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {
    List<Viaggio> findByData(LocalDate data);

    List<Viaggio> findByCompletato(boolean completato);

}
