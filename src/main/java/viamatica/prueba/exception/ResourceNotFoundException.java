package viamatica.prueba.exception;

import java.lang.RuntimeException;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(Object id,String fieldName,String resourceName){
        super(String.format("%s no encontrada con %s %s", resourceName,fieldName,id));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = id;
    }
}