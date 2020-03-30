package com.edu.lnu.mongoDbPpoject.exception;

public class UserNotFoundByUsername extends RuntimeException {
    public UserNotFoundByUsername(String message){
        super(message);
    }
}
