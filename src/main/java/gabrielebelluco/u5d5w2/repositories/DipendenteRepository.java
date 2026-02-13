package gabrielebelluco.u5d5w2.repositories;

import gabrielebelluco.u5d5w2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {
    boolean existsByUsername(String username);

    Optional<Dipendente> findByEmail(String email);

    boolean existsByEmail(String email);
}
