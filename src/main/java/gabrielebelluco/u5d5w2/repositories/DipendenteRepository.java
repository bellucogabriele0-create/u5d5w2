package gabrielebelluco.u5d5w2.repositories;

import gabrielebelluco.u5d5w2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    List<Dipendente> fndByDipendente(Dipendente dipendente);
}
