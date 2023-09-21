package viamatica.prueba.core.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import viamatica.prueba.core.auth.domain.Authentication;
import viamatica.prueba.domain.Response;
import viamatica.prueba.domain.ResponseToken;
import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.person.PersonRepository;
import viamatica.prueba.module.person.domain.Person;
import viamatica.prueba.module.user.UserService;
import viamatica.prueba.module.user.domain.User;
import viamatica.prueba.util.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired 
    private JwtUtils jwtUtils;
    
    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;
    
    // Regsitro
    public Response singup(Person user) {

        user.setIdPersona(Long.MIN_VALUE);
        
        String encode = passwordEncoder.encode(user.getUser().getPassword());
        
        user.getUser().setPassword(encode);
        
        personRepository.save(user);

        return new Response("User created");
    }

     //Authenticacion it return a json web token
    public ResponseToken authenticate(Authentication auth) throws RuntimeException {

        User user = userService.findByUsernameOrMail(auth.getUsername());

        user.verifyFailedAuth();

        boolean pass = passwordEncoder
                .matches(auth.getPassword(),user.getPassword());

        if (!pass) {

            user.sumlogueosIncorrectos();
            userService.update(user);

            throw new ResourceNotFoundException("", "usuario o contrasena", "user");
        }

        String token = jwtUtils.generateToken(auth.getUsername());

        var res = new ResponseToken(auth.getUsername(), token, "ok");

        return res;
    };

}