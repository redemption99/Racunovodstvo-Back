package rs.raf.demo.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rs.raf.demo.model.Transakcija;
import rs.raf.demo.services.ITransakcijaService;
import rs.raf.demo.services.impl.TransakcijaService;
import rs.raf.demo.utils.SearchUtil;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/transakcije")
public class TransakcijaController {

    private final ITransakcijaService transakcijaService;

    private final SearchUtil<Transakcija> searchUtil;

    public TransakcijaController(TransakcijaService transakcijaService) {
        this.transakcijaService = transakcijaService;
        this.searchUtil = new SearchUtil<>();
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(transakcijaService.findAll());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTransakcija(@Valid @RequestBody Transakcija transakcija) {
        if (transakcijaService.findById(transakcija.getTransakcijaId()).isPresent())
            return ResponseEntity.ok(transakcijaService.save(transakcija));
        throw new EntityNotFoundException();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransakcija(@Valid @RequestBody Transakcija transakcija) {
        return ResponseEntity.ok(transakcijaService.save(transakcija));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTransakcija(@PathVariable("id") Long id) {
        if (transakcijaService.findById(id).isPresent()) {
            transakcijaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        throw new EntityNotFoundException();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransakcijaId(@PathVariable("id") Long id) {
        if (transakcijaService.findById(id).isPresent()) {
            return ResponseEntity.ok(transakcijaService.findById(id).get());
        }

        throw new EntityNotFoundException();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@RequestParam(name = "search") String search) {
        Specification<Transakcija> spec = this.searchUtil.getSpec(search);

        List<Transakcija> result = transakcijaService.findAll(spec);

        return ResponseEntity.ok(result);
    }
}
