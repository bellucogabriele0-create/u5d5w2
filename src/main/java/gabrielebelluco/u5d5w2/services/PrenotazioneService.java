package gabrielebelluco.u5d5w2.services;

import gabrielebelluco.u5d5w2.entities.Dipendente;
import gabrielebelluco.u5d5w2.entities.Prenotazione;
import gabrielebelluco.u5d5w2.entities.Viaggio;
import gabrielebelluco.u5d5w2.exceptions.BadRequestException;
import gabrielebelluco.u5d5w2.exceptions.NotFoundException;
import gabrielebelluco.u5d5w2.payloads.PrenotazioneDTO;
import gabrielebelluco.u5d5w2.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final ViaggioService viaggioService;
    private final DipendenteService dipendenteService;

    @Autowired
    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository,
                               ViaggioService viaggioService,
                               DipendenteService dipendenteService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.viaggioService = viaggioService;
        this.dipendenteService = dipendenteService;
    }

    public Prenotazione save(PrenotazioneDTO payload) {
        Viaggio viaggio = viaggioService.findById(payload.viaggioid());
        Dipendente dipendente = dipendenteService.findById(payload.dipendenteid());
        List<Prenotazione> existing = prenotazioneRepository.findByDipendenteDipendenteIdAndDataRichiesta(
                dipendente.getDipendenteId(), payload.dataRichiesta());
        if (!existing.isEmpty()) {
            throw new BadRequestException("ha già prenotato");
        }
        Prenotazione prenotazione = new Prenotazione(payload.dataRichiesta(), payload.notePreferenze(), viaggio, dipendente);
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione findById(UUID prenotazioneId) {
        return prenotazioneRepository.findById(prenotazioneId)
                .orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    public Prenotazione findByIdAndUpdate(UUID prenotazioneId, PrenotazioneDTO payload) {
        Prenotazione found = findById(prenotazioneId);
        Viaggio viaggio = viaggioService.findById(payload.viaggioid());
        Dipendente dipendente = dipendenteService.findById(payload.dipendenteid());
        List<Prenotazione> existing = prenotazioneRepository.findByDipendenteDipendenteIdAndDataRichiesta(
                dipendente.getDipendenteId(), payload.dataRichiesta());
        if (existing.isEmpty()) {
            throw new BadRequestException("ha già prenotato");
        }
        found.setViaggio(viaggio);
        found.setDipendente(dipendente);
        found.setDataRichiesta(payload.dataRichiesta());
        return prenotazioneRepository.save(found);
    }

    public void findByIdAndDelete(UUID prenotazioneId) {
        Prenotazione found = findById(prenotazioneId);
        prenotazioneRepository.delete(found);
    }
}
