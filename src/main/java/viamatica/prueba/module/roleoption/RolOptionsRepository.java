package viamatica.prueba.module.roleoption;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import viamatica.prueba.module.roleoption.domain.RolOptions;

public interface RolOptionsRepository extends JpaRepository<RolOptions, Long> {


    Page<RolOptions> findAll(Pageable pageable);

    
}
