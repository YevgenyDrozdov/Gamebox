package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemGameRepository implements GameRepository {

    public static final String PROPERTY_NAME_REPOSITORY_PATH = "FileSystemGameRepository.RepositoryPath";

    private GameRepositoryConfiguration repositoryConfiguration;
    private GameLocator<File> gameLocator;
    private GameBuilder<File> gameBuilder;

    @Override
    public void init(GameRepositoryConfiguration configuration,
                     GameLocator gameLocator, GameBuilder gameBuilder) {
        repositoryConfiguration = configuration;
        this.gameLocator = gameLocator;
        this.gameBuilder = gameBuilder;
    }

    @Override
    public List<Game> getAllGames() {

        List<Game> games = new ArrayList<Game>();

        File gamesRoot = getRepositoryFileSystemPath();
        File[] files = gamesRoot.listFiles();

        for (File file : files) {
            if (gameLocator.isGame(file)) {
                games.add(gameBuilder.buildGame(file));
            }
        }

        return games;
    }

    @Override
    public Game getGameById(String id) {
        return null;
    }

    protected File getRepositoryFileSystemPath() {
        String repositoryPath = getRepositoryConfiguration().getParameter(PROPERTY_NAME_REPOSITORY_PATH);
        if (repositoryPath != null) {
            return new File(repositoryPath);
        }

        throw new RuntimeException("The path to the repository is not set.");
    }

    protected GameRepositoryConfiguration getRepositoryConfiguration() {
        return repositoryConfiguration;
    }

    protected GameLocator getGameLocator() {
        return gameLocator;
    }

    protected GameBuilder getGameBuilder() {
        return gameBuilder;
    }

}
