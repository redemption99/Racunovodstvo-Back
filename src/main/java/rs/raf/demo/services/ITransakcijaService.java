package rs.raf.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import rs.raf.demo.model.Transakcija;

import java.util.List;

public interface ITransakcijaService extends IService<Transakcija, Long> {

    List<Transakcija> findAll(Specification<Transakcija> spec);

    Page<Transakcija> findAll(Pageable pageSort);
}
