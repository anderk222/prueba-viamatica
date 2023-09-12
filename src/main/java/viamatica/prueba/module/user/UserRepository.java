package viamatica.prueba.module.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import viamatica.prueba.module.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameOrMail(String username, String mail);
    
}
