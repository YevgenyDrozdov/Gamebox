package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.GameIdGenerator;
import com.epam.jmp.gamebox.GameRepository;

import java.util.HashMap;
import java.util.Map;

public class GameRepositoryImpl implements GameRepository {

    private Map<String, GameDescriptor> deployedGames = new HashMap<String, GameDescriptor>();
    private GameIdGenerator gameIdGenerator;

    public GameRepositoryImpl(GameIdGenerator gameIdGenerator) {
        this.gameIdGenerator = gameIdGenerator;
    }

    @Override
    public String addDeployedGame(GameDescriptor gameDescriptor) {
        String gameId = getGameIdByDescriptor(gameDescriptor);

        if (gameId == null) {
            gameId = getGameIdGenerator().generateId(gameDescriptor);

            deployedGames.put(gameId, gameDescriptor);
        }

        return gameId;
    }

    @Override
    public Map<String, GameDescriptor> getAllDeployedGames() {
        return deployedGames;
    }

    public GameIdGenerator getGameIdGenerator() {
        return gameIdGenerator;
    }

    public void setGameIdGenerator(GameIdGenerator gameIdGenerator) {
        this.gameIdGenerator = gameIdGenerator;
    }

    private String getGameIdByDescriptor(GameDescriptor descriptor) {
        for (Map.Entry<String, GameDescriptor> entry : deployedGames.entrySet()) {
            if (descriptor == entry.getValue()) {
                return entry.getKey();
            }
        }

        return null;
    }

}
