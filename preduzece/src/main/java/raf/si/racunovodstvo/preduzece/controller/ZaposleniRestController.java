package raf.si.racunovodstvo.preduzece.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import raf.si.racunovodstvo.preduzece.model.Zaposleni;
import raf.si.racunovodstvo.preduzece.services.IZaposleniService;
import raf.si.racunovodstvo.preduzece.specifications.RacunSpecificationsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@CrossOrigin
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/zaposleni")
public class ZaposleniRestController {

    private final IZaposleniService iZaposleniService;

    public ZaposleniRestController(IZaposleniService iZaposleniService) {
        this.iZaposleniService = iZaposleniService;

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createZaposleni(@Valid @RequestBody Zaposleni zaposleni) {
        return ResponseEntity.ok(iZaposleniService.save(zaposleni));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateZaposleni(@Valid @RequestBody Zaposleni zaposleni,  @PathVariable Long id) {
        if (iZaposleniService.findById(id).isPresent())
            return ResponseEntity.ok(iZaposleniService.updateZaposleni(zaposleni));
        throw new EntityNotFoundException();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> zaposleniOtkaz(@PathVariable("id") Long id) {
        Optional<Zaposleni> optionalZaposleni = iZaposleniService.findById(id);
        if(optionalZaposleni.isPresent()){
            return ResponseEntity.ok(iZaposleniService.otkazZaposleni(optionalZaposleni.get()));
        }
        throw new EntityNotFoundException();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getZaposleniId(@PathVariable("id") Long id) {
        Optional<Zaposleni> optionalZaposleni = iZaposleniService.findById(id);
        if (optionalZaposleni.isPresent()) {
            return ResponseEntity.ok(iZaposleniService.findById(id));
        }
        throw new EntityNotFoundException();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@RequestParam(name = "search") String search) {
        RacunSpecificationsBuilder<Zaposleni> builder = new RacunSpecificationsBuilder<>();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Zaposleni> spec = builder.build();

        List<Zaposleni> result = iZaposleniService.findAll(spec);

        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return ResponseEntity.ok(result);
    }
}