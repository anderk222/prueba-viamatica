package viamatica.prueba.module.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import viamatica.prueba.module.role.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    List<Role> findAll();

    // List<Role> findByUsersIdUsuario(long userid);

}