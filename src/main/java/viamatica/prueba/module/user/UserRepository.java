package viamatica.prueba.module.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import viamatica.prueba.module.user.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameOrMail(String username, String mail);

    // @Query(name = "UPDATE usuarios SET logueos_incorrectos=:val WHERE id_usuario=:id RETURNING.*", nativeQuery = true)
    // User update(@Param("logueos_incorrectos") int logueosIcorrectos, @Param("id") long id);
    
    List<User> findByMailContaining(String mail, Pageable pageable);

    List<User> findByMailContainingOrderByMailAsc(String mail);   
}