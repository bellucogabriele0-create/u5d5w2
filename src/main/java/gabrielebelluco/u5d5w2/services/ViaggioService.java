package gabrielebelluco.u5d5w2.services;

import gabrielebelluco.u5d5w2.entities.Viaggio;
import gabrielebelluco.u5d5w2.exceptions.NotFoundException;
import gabrielebelluco.u5d5w2.payloads.ViaggioDTO;
import gabrielebelluco.u5d5w2.repositories.ViaggioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ViaggioService {
    private final ViaggioRepository viaggioRepository;

    @Autowired
    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Viaggio save(ViaggioDTO payload) {
        Viaggio newViaggio = new Viaggio(false, payload.data(), payload.destinazione());
        Viaggio saved = this.viaggioRepository.save(newViaggio);
        log.info("il viaggio con id " + saved.getId() + " è stato salvato ");

        return saved;
    }

    public Page<Viaggio> findAll(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("asc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));

        return this.viaggioRepository.findAll(pageable);
    }

    public Viaggio findById(UUID viaggioId) {
        return this.viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException(viaggioId));
    }

    public Viaggio findByIdAndUpdate(UUID Id, ViaggioDTO payload) {
        Viaggio found = this.findById(Id);
        found.setDestinazione(payload.destinazione());
        found.setData(payload.data());
        Viaggio mod = this.viaggioRepository.save(found);
        log.info("l viaggio con id " + mod.getId() + " è stato modificato ");
        return mod;
    }

    public void findByIdAndDelete(UUID viaggioId) {
        Viaggio found = this.findById(viaggioId);
        this.viaggioRepository.delete(found);
    }

    public Viaggio changeStatus(UUID Id, boolean completato) {
        Viaggio found = this.findById(Id);
        found.setCompletato(completato);
        Viaggio updated = this.viaggioRepository.save(found);
        log.info("lo stato del viaggio con id " + updated.getId() + " è  aggiornato");
        return updated;
    }
}

