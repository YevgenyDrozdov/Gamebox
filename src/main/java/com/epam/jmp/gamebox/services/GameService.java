package com.epam.jmp.gamebox.services;

import com.epam.jmp.gamebox.Game;
import java.util.Map;

public interface GameService {

    void refreshDeployments();
    Map<String, Game> getAllDeployedGames();
    Game getDeployedGameById(String gameId);
    Game getInstantiatedGameById(String gameId);

}
