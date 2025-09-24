package io.github.Erissonteixeira.api_crudnarutoII.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
