package viamatica.prueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthAttempsExceededException extends RuntimeException {

    public AuthAttempsExceededException() {
        super("Itentos de autentificacion excedidos");
    }

    public AuthAttempsExceededException(String message) {
        super("Itentos de autentificacion excedidos");
    }
}