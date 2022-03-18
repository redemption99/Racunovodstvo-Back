package rs.raf.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.demo.model.Permission;

public interface FakturaRepository extends JpaRepository<Permission, Long> {
}
