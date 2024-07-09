package com.dyma.tennis.service;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String lastName) {
        super("Payer with lastname" + lastName + "could not be found" );
    }
}
