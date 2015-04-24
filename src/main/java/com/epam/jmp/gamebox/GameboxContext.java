package com.epam.jmp.gamebox;

import com.epam.jmp.gamebox.deploy.*;
import com.epam.jmp.gamebox.impl.GameRepositoryImpl;
import com.epam.jmp.gamebox.impl.GameIdGeneratorImpl;
import com.epam.jmp.gamebox.impl.GameServiceImpl;
import com.epam.jmp.gamebox.impl.LoadedGameRepositoryImpl;
import com.epam.jmp.gamebox.services.GameService;
import com.epam.jmp.gamebox.war.deploy.WarGameDeployAssistant;
import com.epam.jmp.gamebox.war.deploy.WarXmlDeploymentDescriptorLocator;
import com.epam.jmp.gamebox.war.loader.WarGameLoader;

import java.util.HashMap;
import java.util.Map;

public final class GameboxContext {

    private GameRepository deployedGameRepository;
    private GameRepository instantiatedGameRepository;
    private GameDeployer gameDeployer;
    private GameLoader gameLoader;
    private GameService gameService;

    private GameboxContext() {
        initializeDeployedGameRepository();
        initializeLoadedGameRepository();
        initializeGameDeployer();
        initializeGameLoader();
        initializeGameService();
    }

    public static GameboxContext getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public GameService getGameService() {
        return gameService;
    }

    private void initializeDeployedGameRepository() {
        deployedGameRepository = new GameRepositoryImpl(new GameIdGeneratorImpl());
    }

    private void initializeLoadedGameRepository() {
        instantiatedGameRepository = new GameRepositoryImpl();
    }

    private void initializeGameLoader() {
        gameLoader = new WarGameLoader();
    }

    private void initializeGameDeployer() {
        GameDistributionItemFactory distributionItemFactory = new FileSystemGameDistributionItemFactory();
        GameDeployerConfiguration deployerConfiguration = new SystemPropertiesGameDeployerConfiguration();
        FileSystemGameDeployer deployer = new FileSystemGameDeployer();

        WarXmlDeploymentDescriptorLocator deploymentDescriptorLocator = new WarXmlDeploymentDescriptorLocator();
        DeployAssistant assistant = new WarGameDeployAssistant(deploymentDescriptorLocator);
        Map<GameDistributionType, DeployAssistant> assistants = new HashMap<GameDistributionType, DeployAssistant>();
        assistants.put(GameDistributionType.WAR, assistant);

        deployer.setDeployAssistants(assistants);
        deployer.init(deployerConfiguration, distributionItemFactory);

        gameDeployer = deployer;
    }

    private void initializeGameService() {
        gameService = new GameServiceImpl(deployedGameRepository, instantiatedGameRepository, gameDeployer, gameLoader);
    }

    private static class InstanceHolder {
        public static final GameboxContext INSTANCE = new GameboxContext();
    }

}
