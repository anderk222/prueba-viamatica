package viamatica.prueba.module.session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import viamatica.prueba.module.session.domain.Session;


public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findAll();


}