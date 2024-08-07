package com.dyma.tennis.service;

import com.dyma.tennis.Player;
import com.dyma.tennis.PlayerList;
import com.dyma.tennis.web.PlayerToRegister;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    public List<Player> getAllPlayers() {
        return PlayerList.ALL.stream()
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .collect(Collectors.toList());

    }

    public Player getByLastName(String lastName) {
        return PlayerList.ALL.stream()
                .filter(player -> player.lastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(lastName));
    }

    public Player create(PlayerToRegister playerToRegister) {
        return getPlayerNewRanking(PlayerList.ALL, playerToRegister);
    }

    public Player update(PlayerToRegister playerToRegister) {
        getByLastName(playerToRegister.lastName());
        // enlever le joueur qu'on veut mettre à jour
        List<Player> playerWithoutPlayerToUpdate = PlayerList.ALL.stream()
                .filter(player -> !player.lastName().equals(playerToRegister.lastName()))
                .toList();
        return getPlayerNewRanking(playerWithoutPlayerToUpdate, playerToRegister);
    }
    public void delete(String lastName){
        Player playerToDelete= getByLastName(lastName);
        PlayerList.ALL=PlayerList.ALL.stream()
                .filter(player -> !player.lastName().equals(lastName))
                .toList();
        RankingCalculator rankingCalculator= new RankingCalculator(PlayerList.ALL);
        rankingCalculator.getNewPlayerRanking();
    }
    private Player getPlayerNewRanking (List<Player> existingPlayer, PlayerToRegister playerToRegister){
        RankingCalculator rankingCalculator = new RankingCalculator(existingPlayer, playerToRegister);
        List <Player> players = rankingCalculator.getNewPlayerRanking();
        return players.stream()
                .filter(player -> player.lastName().equals(playerToRegister.lastName()))
                .findFirst().get();
    }
}
