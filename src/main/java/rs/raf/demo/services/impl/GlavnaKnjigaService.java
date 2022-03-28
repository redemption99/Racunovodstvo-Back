package rs.raf.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.demo.model.DnevnikKnjizenja;
import rs.raf.demo.model.GlavnaKnjiga;
import rs.raf.demo.repositories.GlavnaKnjigaRepository;
import rs.raf.demo.services.IDnevnikKnjizenjaService;
import rs.raf.demo.services.IGlavnaKnjigaService;

import java.util.List;
import java.util.Optional;

@Service
public class GlavnaKnjigaService implements IGlavnaKnjigaService {

    private final GlavnaKnjigaRepository glavnaKnjigaRepository;

    @Autowired
    public GlavnaKnjigaService(GlavnaKnjigaRepository glavnaKnjigaRepository) {
        this.glavnaKnjigaRepository = glavnaKnjigaRepository;
    }


    @Override
    public <S extends GlavnaKnjiga> S save(S var1) {
        return glavnaKnjigaRepository.save(var1);
    }

    @Override
    public Optional<GlavnaKnjiga> findById(Long id) {
        return glavnaKnjigaRepository.findById(id);
    }

    @Override
    public List<GlavnaKnjiga> findAll() {
        return glavnaKnjigaRepository.findAll();
    }

    @Override
    public void deleteById(Long var1) {

    }

    @Override
    public Double getSumaDuguje(Long GlavnaKnjigaId) {
        return null;
    }

    @Override
    public Double getSumaPotrazuje(Long GlavnaKnjigaId) {
        return null;
    }

    @Override
    public Double getSaldo(Long GlavnaKnjigaId) {
        return null;
    }
}
