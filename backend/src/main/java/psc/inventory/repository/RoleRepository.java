package psc.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psc.inventory.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
