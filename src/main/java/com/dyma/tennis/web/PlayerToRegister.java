package com.dyma.tennis.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.time.LocalDate;

public record PlayerToRegister(
        @NotBlank(message = "First name is mandatory") String firstName,
        @NotBlank(message= "Last name is mandatory") String lastName,
        @NotNull(message= " Birth date is mandatory") @PastOrPresent(message = "Birth Date must be past or present") LocalDate birthDate,
        @PositiveOrZero(message="Points must be more than zero") int points) {
}
