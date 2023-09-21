package viamatica.prueba.module.person;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.person.domain.Person;
import viamatica.prueba.module.user.UserService;
import viamatica.prueba.module.user.domain.User;
import viamatica.prueba.util.EmailGenerator;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    UserService userService;

    @Autowired 
    private BCryptPasswordEncoder encoder;
    

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

        EmailGenerator generator = new EmailGenerator();

        String email = generator.generate(person.getFullName());
        String initital = email.split("@")[0];
        
        Sort sort = Sort.by(Sort.Direction.DESC, "idUsuario");
        
        List<User> users = userService.findByMail(initital, sort);

        String encode = encoder.encode(person.getUser().getPassword());
        
        person.getUser().setPassword(encode);

        if(users.isEmpty() == false){
        
            email = generator.generateFromEmail(users.get(0).getMail());
            
        }
        
        
        
        person.getUser().setMail(email);

        

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
    }

    ;

    // Eliminar persona por id

    public Person delete(long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "person"));

        personRepository.deleteById(id);

        return person;

    }

}
