package rs.raf.demo.services;

import rs.raf.demo.model.DnevnikKnjizenja;

import java.util.List;

public interface IDnevnikKnjizenjaService extends IService<DnevnikKnjizenja, Long>{

    List<DnevnikKnjizenja> findAll();

    Double getSumaDuguje(Long dnevnikKnjizenjaId);

    Double getSumaPotrazuje(Long dnevnikKnjizenjaId);

    Double getSaldo(Long dnevnikKnjizenjaId);
}
