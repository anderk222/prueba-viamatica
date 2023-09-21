package viamatica.prueba.module.role;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import viamatica.prueba.module.role.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    List<Role> findAll();

     Page<Role> findAll(Pageable pageable);

    List<Role> findByUsersIdUsuario(long userid);


    

}