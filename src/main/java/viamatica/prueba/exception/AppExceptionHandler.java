package viamatica.prueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import viamatica.prueba.domain.ErrorResponse;

@ControllerAdvice
public class AppExceptionHandler {

    // handler ResourceNotFoundException

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResouceNotFound(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // handler AuthAttempsExceededException

    @ExceptionHandler(AuthAttempsExceededException.class)
    public ResponseEntity<ErrorResponse> handleResouceNotFound(AuthAttempsExceededException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

   // handler handleConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {

        StringBuilder errors = new StringBuilder(); 
        for(ConstraintViolation<?> v : ex.getConstraintViolations() ){
            errors.append(v.getPropertyPath()).append('=').append(v.getMessage()).append('\n');
        }

        ErrorResponse errorResponse = new ErrorResponse(errors.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

       // handler handleConstraintViolationException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(RuntimeException ex) {


        ex.printStackTrace();
        
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
