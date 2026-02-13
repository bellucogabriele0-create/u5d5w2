package gabrielebelluco.u5d5w2.repositories;

import gabrielebelluco.u5d5w2.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
    // List<Viaggio> fndByViaggio(Viaggio viaggio);

}
