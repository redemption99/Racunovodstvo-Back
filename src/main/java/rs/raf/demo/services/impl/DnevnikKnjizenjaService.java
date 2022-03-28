package rs.raf.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.demo.model.DnevnikKnjizenja;
import rs.raf.demo.model.Faktura;
import rs.raf.demo.model.TipFakture;
import rs.raf.demo.repositories.DnevnikKnjizenjaRepository;
import rs.raf.demo.services.IDnevnikKnjizenjaService;

import java.util.List;
import java.util.Optional;

@Service
public class DnevnikKnjizenjaService implements IDnevnikKnjizenjaService {

    private final DnevnikKnjizenjaRepository dnevnikKnjizenjaRepository;

    @Autowired
    public DnevnikKnjizenjaService(DnevnikKnjizenjaRepository dnevnikKnjizenjaRepository) {
        this.dnevnikKnjizenjaRepository = dnevnikKnjizenjaRepository;
    }

    @Override
    public List<DnevnikKnjizenja> findAll() {
        return dnevnikKnjizenjaRepository.findAll();
    }

    @Override
    public Double getSumaDuguje(Long dnevnikKnjizenjaId) {
        Optional<DnevnikKnjizenja> tr = findById(dnevnikKnjizenjaId);
        Faktura faktura = tr.get().getFaktura();
        Double suma = Double.valueOf(0);
        if (faktura.getTipFakture().equals(TipFakture.ULAZNA_FAKTURA))
            suma += faktura.getNaplata();
        return suma;
    }

    @Override
    public Double getSumaPotrazuje(Long dnevnikKnjizenjaId) {
        Optional<DnevnikKnjizenja> tr = findById(dnevnikKnjizenjaId);
        Faktura faktura = tr.get().getFaktura();
        Double suma = Double.valueOf(0);
        if (faktura.getTipFakture().equals(TipFakture.IZLAZNA_FAKTURA))
            suma += faktura.getNaplata();
        return suma;
    }

    @Override
    public Double getSaldo(Long dnevnikKnjizenjaId) {
        return getSumaPotrazuje(dnevnikKnjizenjaId)-getSumaDuguje(dnevnikKnjizenjaId);
    }

    @Override
    public <S extends DnevnikKnjizenja> S save(S var1) {
        return dnevnikKnjizenjaRepository.save(var1);
    }

    @Override
    public Optional<DnevnikKnjizenja> findById(Long id) {
        return dnevnikKnjizenjaRepository.findByDnevnikKnjizenjaId(id);
    }

    @Override
    public void deleteById(Long var1) {

    }
}
