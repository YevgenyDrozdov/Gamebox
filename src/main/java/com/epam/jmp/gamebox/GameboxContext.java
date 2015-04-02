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

    private static class InstanceHolder {
        public static final GameboxContext INSTANCE = new GameboxContext();
    }
}
