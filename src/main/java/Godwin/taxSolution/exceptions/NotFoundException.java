package Godwin.taxSolution.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(int id){
        super("This element with id " + id + " was not found");
    }
    public NotFoundException(String message){
        super("This element with id " + message + " was not found");
    }
}
