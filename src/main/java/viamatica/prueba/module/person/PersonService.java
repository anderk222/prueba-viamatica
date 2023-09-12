package viamatica.prueba.module.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.person.domain.Person;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // Traer personas con paginacion

    public Pagination<Person> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Person> data = personRepository.findAll(pageable);

        Pagination<Person> res = new Pagination<>(page, size, data.getContent());
        res.setTotalItems(data.getTotalElements());
        res.setTotalPages(data.getTotalPages());

        return res;
    }

    // Encontrar persona por id

    public Person find(long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "person"));

        return person;

    }

    // Guardar persona 

    public Person save(Person person) {

        person.setIdPersona(Long.MIN_VALUE);

        return personRepository.save(person);

    }

    // Editar persona

    public Person update(Person person, long id) {

        Person updated = personRepository.findById(id).map((_person) -> {

            person.setIdPersona(id);

            return personRepository.save(person);
        })
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "person"));

        return updated;
    };

    // Eliminar persona por id

    public Person delete(long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "person"));

        personRepository.deleteById(id);

        return person;

    }

}