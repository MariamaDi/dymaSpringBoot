package com.dyma.tennis.service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.dyma.tennis.Player;
import com.dyma.tennis.PlayerList;
import com.dyma.tennis.Rank;
import com.dyma.tennis.web.PlayerToRegister;
public class RankingCalculator {
    private final List<Player> currentPlayerRanking;
    private final PlayerToRegister playerToRegister;
    public RankingCalculator(List<Player> currentPlayerRanking, PlayerToRegister playerToRegister) {
        this.currentPlayerRanking = currentPlayerRanking;
        this.playerToRegister = playerToRegister;
    }
    public RankingCalculator(List<Player> currentPlayerRanking) {
        this.currentPlayerRanking = currentPlayerRanking;
        this.playerToRegister = null;
    }
// on rajoute le nouveau joueur dans la liste des joueurs actuels
    public List<Player> getNewPlayerRanking() {
        List<Player> newRankingList = new ArrayList<>(currentPlayerRanking);
        if(playerToRegister != null) {
            newRankingList.add(new Player(
                    playerToRegister.firstName(),
                    playerToRegister.lastName(),
                    playerToRegister.birthDate(),
                 null
            ));
        }
        newRankingList.sort((player1,player2) -> Integer.compare(player2.rank().points(), player1.rank().points()));
        //on trie la liste des joueurs en fonction de leurs points
//        List<Player> sortedPlayers = newRankingList.stream()
//                .sorted(Comparator.comparing(player -> player.rank().points()))
//                .toList();
    List<Player> updatedPlayers = new ArrayList<>();
    for(int i = 0; i<newRankingList.size();i++)
    {   // Obligée de recréer un player car  les attributs d'un record ne peut etre modifié
        Player player = newRankingList.get(i);
        Player updatedPlayer = new Player(
                player.firstName(),
                player.lastName(),
                player.birthDate(),
                new Rank(i + 1, player.rank().points())
        );
        updatedPlayers.add(updatedPlayer);
    }
        PlayerList.ALL=updatedPlayers;
    return updatedPlayers;
}
}
