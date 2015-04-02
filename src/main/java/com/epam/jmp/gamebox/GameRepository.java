package com.epam.jmp.gamebox;

import java.util.Map;

public interface GameRepository {

    String addDeployedGame(GameDescriptor gameDescriptor);
    Map<String, GameDescriptor> getAllDeployedGames();

}
