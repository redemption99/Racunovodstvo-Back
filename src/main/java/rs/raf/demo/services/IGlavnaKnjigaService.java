package rs.raf.demo.services;

import rs.raf.demo.model.GlavnaKnjiga;

import java.util.List;

public interface IGlavnaKnjigaService extends IService<GlavnaKnjiga, Long>{

    List<GlavnaKnjiga> findAll();

    Double getSumaDuguje(Long GlavnaKnjigaId);

    Double getSumaPotrazuje(Long GlavnaKnjigaId);

    Double getSaldo(Long GlavnaKnjigaId);
}
