package viamatica.prueba.module.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.module.person.domain.Person;

@RestController
@RequestMapping("/api/person")
@CrossOrigin({"*"})
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping()
    public Pagination<Person> findAlll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

        return personService.findAll(page, size);

    }

    @GetMapping("/{id}")
    public Person find(@PathVariable long id) {

        return personService.find(id);

    }

    @PostMapping()
    public ResponseEntity<Person> save(@RequestBody Person person) {

        Person saved = personService.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable long id, @RequestBody Person person) {

        return personService.update(person, id);

    }

    @DeleteMapping("/{id}")
    public Person delete(@PathVariable long id) {

        return personService.delete(id);

    }

}
