package library.buildrun.librarySecurity.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.buildrun.librarySecurity.entities.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

	Roles findByName(String name);
}
