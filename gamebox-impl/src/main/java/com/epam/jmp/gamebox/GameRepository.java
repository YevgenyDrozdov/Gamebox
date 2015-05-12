package com.epam.jmp.gamebox;

import java.util.Map;

public interface GameRepository {

    String addGame(Game game);
    String addGame(Game game, String gameId);
    String getGameId(Game game);
    Game getGameById(String gameId);
    Map<String, Game> getAllGames();
    Game removeGame(String gameId);

}
