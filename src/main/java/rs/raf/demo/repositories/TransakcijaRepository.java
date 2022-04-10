package rs.raf.demo.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.raf.demo.model.Transakcija;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransakcijaRepository extends JpaRepository<Transakcija, Long> {

    public List<Transakcija> findAll();

    public Optional<Transakcija> findByBrojTransakcije(Long brojTransakcije);

    List<Transakcija> findAll(Specification<Transakcija> spec);
}
