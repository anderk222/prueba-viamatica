package viamatica.prueba.exception;


public class AuthAttempsExceededException extends RuntimeException {

    public AuthAttempsExceededException() {
        super("Itentos de autentificacion excedidos");
    }

    public AuthAttempsExceededException(String message) {
        super("Itentos de autentificacion excedidos");
    }
}