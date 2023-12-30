package com.example.Vaccine.Distribution.System.exceptions;

public class WrongCredentialException extends RuntimeException{
    public WrongCredentialException(String msg){
        super(msg);
    }
}
