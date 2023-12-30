package com.example.Vaccine.Distribution.System.exceptions;

public class PatientDoesNotExistException extends RuntimeException{
    public PatientDoesNotExistException(String msg){
        super(msg);
    }
}
