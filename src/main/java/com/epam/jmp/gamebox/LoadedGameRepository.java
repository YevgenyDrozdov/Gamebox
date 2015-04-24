package com.epam.jmp.gamebox;

public interface LoadedGameRepository {

    void addGame(String gameId, Game game);
    Game getGameById(String gameId);

}
