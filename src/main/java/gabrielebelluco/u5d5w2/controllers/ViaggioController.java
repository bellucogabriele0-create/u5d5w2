package gabrielebelluco.u5d5w2.controllers;

import gabrielebelluco.u5d5w2.entities.Viaggio;
import gabrielebelluco.u5d5w2.exceptions.BadRequestException;
import gabrielebelluco.u5d5w2.payloads.ViaggioDTO;
import gabrielebelluco.u5d5w2.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    private final ViaggioService viaggioService;

    @Autowired
    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio creazzioneViaggio(@RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new BadRequestException("lista errori");
        } else {
            return this.viaggioService.save(payload);
        }
    }


    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable UUID viaggioId) {
        return this.viaggioService.findById(viaggioId);
    }

    @PutMapping("/{viaggioId}")
    public Viaggio findByIdAndUpdate(@PathVariable UUID viaggioId, @RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new BadRequestException("lista errori");
        } else {
            return this.viaggioService.findByIdAndUpdate(viaggioId, payload);
        }
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID viaggioId) {
        this.viaggioService.findByIdAndDelete(viaggioId);
    }


    // TODO da fixare
    @PatchMapping("/{viaggioId}/stato")
    public Viaggio changeStatus(@PathVariable UUID viaggioId, @RequestParam boolean completato) {
        return this.viaggioService.changeStatus(viaggioId, completato);
    }
}