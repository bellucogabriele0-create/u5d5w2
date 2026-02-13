package gabrielebelluco.u5d5w2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import gabrielebelluco.u5d5w2.entities.Dipendente;
import gabrielebelluco.u5d5w2.exceptions.BadRequestException;
import gabrielebelluco.u5d5w2.exceptions.NotFoundException;
import gabrielebelluco.u5d5w2.payloads.DipendenteDTO;
import gabrielebelluco.u5d5w2.repositories.DipendenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class DipendenteService {
    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinaryUploader;

    @Autowired
    public DipendenteService(DipendenteRepository dipendenteRepository, Cloudinary cloudinaryUploader) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinaryUploader = cloudinaryUploader;
    }

    public Dipendente saveDipendente(DipendenteDTO payload) {
        this.dipendenteRepository.findByEmail(payload.email()).ifPresent(dipendente -> {
            throw new BadRequestException("email " + dipendente.getEmail() + " già utilizzata");
        });

        Dipendente newDipendente = new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email(), payload.immagineDip());
        newDipendente.setImmagineDip("https://ui-avatars.com/api?name=" + payload.nome() + "+" + payload.cognome());
        Dipendente saved = this.dipendenteRepository.save(newDipendente);
        log.info("Il dipendente con id " + saved.getDipendenteId() + " è stato salvato correttamente!");
        return saved;
    }

    public Page<Dipendente> findAll(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(UUID dipendenteId) {
        return this.dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new NotFoundException(dipendenteId));
    }

    public Dipendente findByIdAndUpdate(UUID dipendenteId, DipendenteDTO payload) {
        Dipendente found = this.findById(dipendenteId);

        if (!found.getEmail().equals(payload.email())) {
            this.dipendenteRepository.findByEmail(payload.email()).ifPresent(dipendente -> {
                throw new BadRequestException("email " + dipendente.getEmail() + " già usata");
            });
        }
        found.setUsername(payload.username());
        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setEmail(payload.email());
        found.setImmagineDip("https://ui-avatars.com/api?name=" + payload.nome() + "+" + payload.cognome());
        Dipendente modified = this.dipendenteRepository.save(found);
        log.info("il dipendente con id " + modified.getDipendenteId() + " è stato modificato correttamente");
        return modified;
    }

    public void findByIdAndDelete(UUID dipendenteId) {
        Dipendente found = this.findById(dipendenteId);
        this.dipendenteRepository.delete(found);
    }

    public String uploadAvatar(MultipartFile file) {
        try {
            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("secure_url");
            return imageUrl;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
