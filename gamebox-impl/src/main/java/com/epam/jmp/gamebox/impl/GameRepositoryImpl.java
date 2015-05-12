package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameIdGenerator;
import com.epam.jmp.gamebox.GameRepository;

import java.util.HashMap;
import java.util.Map;

public class GameRepositoryImpl implements GameRepository {

    private Map<String, Game> games = new HashMap<String, Game>();
    private GameIdGenerator gameIdGenerator;

    public GameRepositoryImpl() {
        this.gameIdGenerator = new GameIdGeneratorImpl();
    }

    public GameRepositoryImpl(GameIdGenerator gameIdGenerator) {
        this.gameIdGenerator = gameIdGenerator;
    }

    @Override
    public String addGame(Game game) {
        String gameId = getGameId(game);

        if (gameId == null) {
            gameId = getGameIdGenerator().generateId(game);

            addGame(game, gameId);
        }

        return gameId;
    }

    @Override
    public String addGame(Game game, String gameId) {
        games.put(gameId, game);
        return gameId;
    }

    @Override
    public Game getGameById(String gameId) {
        return games.get(gameId);
    }

    @Override
    public Map<String, Game> getAllGames() {
        return games;
    }

    @Override
    public Game removeGame(String gameId) {
        return games.remove(gameId);
    }

    public GameIdGenerator getGameIdGenerator() {
        return gameIdGenerator;
    }

    @Override
    public String getGameId(Game descriptor) {
        for (Map.Entry<String, Game> entry : games.entrySet()) {
            if (descriptor == entry.getValue()) {
                return entry.getKey();
            }
        }

        return null;
    }

    public void setGameIdGenerator(GameIdGenerator gameIdGenerator) {
        this.gameIdGenerator = gameIdGenerator;
    }

}
