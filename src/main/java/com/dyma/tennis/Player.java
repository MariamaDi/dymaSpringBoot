package com.dyma.tennis;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record Player(
        @NotBlank (message = "firstName ne doit pas etre vide") String firstName,
        @NotBlank (message = "lastName ne doit pas etre vie") String lastName,
        @NotNull(message= "la date de naissance ne doit pas etre vide") @PastOrPresent(message= "la date de naissance ne doit pas etre au futur") LocalDate birthDate,
        @Valid Rank rank) {
}
