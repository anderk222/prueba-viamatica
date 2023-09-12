package viamatica.prueba.module.person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import viamatica.prueba.module.person.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    List<Person> findAll();
    
}
