package viamatica.prueba.core.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import viamatica.prueba.core.auth.domain.Authentication;
import viamatica.prueba.domain.Response;
import viamatica.prueba.domain.ResponseToken;
import viamatica.prueba.module.person.domain.Person;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/singin")
    public ResponseToken authenticate(@RequestBody Authentication authentication) {

        return authService.authenticate(authentication);
    }

    @PostMapping("/singup")
    public ResponseEntity<Response> User(@RequestBody Person user) {


        return ResponseEntity
                .status(HttpStatus.CREATED).body(authService.singup(user));
    }

}
