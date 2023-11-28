package Godwin.taxSolution.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

    public NotFoundException(UUID id){
        super("This element with id " + id + " was not found");
    }
    public NotFoundException(String message){
        super("This element with id " + message + " was not found");
    }
}
