package com.epam.jmp.gamebox;

import java.util.HashMap;
import java.util.Map;

public final class GameboxContext {

    private Map<String, GameDescriptor> deployedGames;

    private GameboxContext() {
        deployedGames = new HashMap<String, GameDescriptor>();
    }

    public static GameboxContext getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public Map<String, GameDescriptor> getAllDeployedGames() {
        return deployedGames;
    }

    public String addDeployedGame(GameDescriptor gameDescriptor) {
        String gameId = getGameIdByDescriptor(gameDescriptor);

        if (gameId == null) {
            gameId = generateGameId(gameDescriptor);

            deployedGames.put(gameId, gameDescriptor);
        }

        return gameId;
    }

    private String getGameIdByDescriptor(GameDescriptor descriptor) {
        for (Map.Entry<String, GameDescriptor> entry : deployedGames.entrySet()) {
            if (descriptor == entry.getValue()) {
                return entry.getKey();
            }
        }

        return null;
    }

    private String generateGameId(GameDescriptor descriptor) {
        return Long.toString(System.currentTimeMillis());
    }

    private static class InstanceHolder {
        public static final GameboxContext INSTANCE = new GameboxContext();
    }
}
