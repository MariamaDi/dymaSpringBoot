package com.dyma.tennis;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Rank(
        @Positive(message = "la position doit etre un entier") int position,
        @PositiveOrZero(message = "Points must be more than zero") int  points) {
}
