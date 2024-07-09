package com.dyma.tennis;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class PlayerList {
    public static Player RAFAEL_NADAL = new Player(
            "Rafael",
            "Nadal",
            LocalDate.of(1986, Month.MAY, 3),
            new Rank(1,5880)
    );

    public static Player NOVAK_DJOKOVIC = new Player(
            "Novak",
            "Djokovic",
            LocalDate.of(1987, Month.AUGUST, 22),
            new Rank(2,4880)
    );

    public static Player ROGER_FEDERER = new Player(
            "Roger",
            "Federer",
            LocalDate.of(1981, Month.AUGUST, 8),
            new Rank(3,3880)
    );

    public static Player ANDY_MURRAY = new Player(
            "ANDY",
            "Murray",
            LocalDate.of(1987, Month.MAY, 15),
            new Rank(4,2880)
    );

public static List<Player> ALL= Arrays.asList(ANDY_MURRAY, ROGER_FEDERER, NOVAK_DJOKOVIC,RAFAEL_NADAL);

}
