package viamatica.prueba.core.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import viamatica.prueba.core.auth.domain.Authentication;
import viamatica.prueba.module.person.domain.Person;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/singin")
    @Transactional
    public String authenticate(@RequestBody Authentication auth) {

        return authService.authenticate(auth);
    }

    @PostMapping("/singup")
    public ResponseEntity<String> User(@RequestBody Person user) {

        authService.singup(user);

        return ResponseEntity
                .status(HttpStatus.CREATED).body("Usuario registrado");

    }



}
