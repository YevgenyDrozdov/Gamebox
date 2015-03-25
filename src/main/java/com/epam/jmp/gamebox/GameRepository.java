package com.epam.jmp.gamebox;

import java.util.List;

public interface GameRepository {

    void init(GameRepositoryConfiguration configuration, GameLocator gameLocator, GameLoader gameLoader);
    List<GameDescriptor> getAllGameDescriptors();
    Game getGameById(String id);

}
