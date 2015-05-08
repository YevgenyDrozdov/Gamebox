package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.*;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
import com.epam.jmp.gamebox.deploy.GameDeployer;
import com.epam.jmp.gamebox.instantiator.GameInstantiator;
import com.epam.jmp.gamebox.services.GameService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GameServiceImpl implements GameService {

    private GameRepository deployedGameRepository;
    private DeploymentRepository deploymentRepository;
    private GameRepository instantiatedGameRepository;
    private GameDeployer gameDeployer;
    private GameLoader gameLoader;
    private GameInstantiator gameInstantiator;

    public GameServiceImpl(GameRepository deployedGameRepository,
                           GameRepository instantiatedGameRepository,
                           GameDeployer gameDeployer,
                           GameLoader gameLoader,
                           DeploymentRepository deploymentRepository,
                           GameInstantiator gameInstantiator) {
        this.deployedGameRepository = deployedGameRepository;
        this.instantiatedGameRepository = instantiatedGameRepository;
        this.gameDeployer = gameDeployer;
        this.gameLoader = gameLoader;
        this.deploymentRepository = deploymentRepository;
        this.gameInstantiator = gameInstantiator;
    }

    @Override
    public void refreshDeployments() {
        List<DeploymentDescriptor> deployedGames = new ArrayList<DeploymentDescriptor>(gameDeployer.refreshDeployments());
        Map<String, Game> alreadyDeployedGames = deployedGameRepository.getAllGames();

        List<String> undeployedGames = new ArrayList<String>();

        for (Map.Entry<String, Game> entry : alreadyDeployedGames.entrySet()) {
            String gameId = entry.getKey();
            Game game = entry.getValue();

            boolean isUndeployed = true;
            Iterator<DeploymentDescriptor> iterator = deployedGames.iterator();
            while (iterator.hasNext()) {
                DeploymentDescriptor currentDeploymentDescriptor = iterator.next();
                if (currentDeploymentDescriptor.getDeploymentId().equals(game.getDescriptor().getDeploymentDescriptor().getDeploymentId())) {
                    iterator.remove();
                    isUndeployed = false;
                }
            }

            if (isUndeployed) {
                undeployedGames.add(gameId);
            }
        }

        for (String gameId : undeployedGames) {
            deployedGameRepository.removeGame(gameId);
        }

        for (DeploymentDescriptor descriptor : deployedGames) {
            Game game = gameLoader.loadGame(descriptor);
            deployedGameRepository.addGame(game);

            deploymentRepository.addDeployment(descriptor);
        }
    }

    @Override
    public Map<String, Game> getAllDeployedGames() {
        return deployedGameRepository.getAllGames();
    }

    @Override
    public Game getDeployedGameById(String gameId) {
        return deployedGameRepository.getGameById(gameId);
    }

    @Override
    public Game instantiateGame(String gameId) {
        Game game = deployedGameRepository.getGameById(gameId);
        game = gameInstantiator.instantiate(game);
        instantiatedGameRepository.addGame(game, gameId);
        return game;
    }

    @Override
    public Game getInstantiatedGameById(String gameId) {
        return instantiatedGameRepository.getGameById(gameId);
    }

}
