package rs.raf.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.demo.model.Permission;

public interface PreduzeceRepository extends JpaRepository<Permission, Long> {
}
