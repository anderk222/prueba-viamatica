package viamatica.prueba.core.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import viamatica.prueba.module.user.UserRepository;

@RestController
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/auth/")
    public String hello(){

        return "hello";
    }

}
