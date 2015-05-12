package com.epam.jmp.gamebox;

import com.epam.jmp.gamebox.classloader.GameboxClassLoader;
import com.epam.jmp.gamebox.deploy.*;
import com.epam.jmp.gamebox.impl.*;
import com.epam.jmp.gamebox.instantiator.DefaultGameInstantiator;
import com.epam.jmp.gamebox.instantiator.GameInstantiator;
import com.epam.jmp.gamebox.loader.GameLoader;
import com.epam.jmp.gamebox.services.DeploymentService;
import com.epam.jmp.gamebox.services.GameService;
import com.epam.jmp.gamebox.war.deploy.WarGameDeployAssistant;
import com.epam.jmp.gamebox.war.deploy.WarXmlDeploymentDescriptorLocator;
import com.epam.jmp.gamebox.war.loader.WarGameLoader;

import java.util.HashMap;
import java.util.Map;

public final class GameboxContext {

    private GameRepository deployedGameRepository;
    private GameRepository instantiatedGameRepository;
    private DeploymentRepository deploymentRepository;
    private GameDeployer gameDeployer;
    private GameLoader gameLoader;
    private GameService gameService;
    private DeploymentService deploymentService;
    private GameInstantiator gameInstantiator;
    private GameboxClassLoader gameboxClassLoader;

    private GameboxContext() {
        initializeGameboxClassLoader();
        initializeDeployedGameRepository();
        initializeLoadedGameRepository();
        initializeDeploymentRepository();
        initializeGameDeployer();
        initializeGameLoader();
        initializeGameInstantiator();
        initializeGameService();
        initializeDeploymentService();
    }

    public static GameboxContext getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public GameService getGameService() {
        return gameService;
    }

    public DeploymentService getDeploymentService() {
        return deploymentService;
    }

    public GameboxClassLoader getGameboxClassLoader() {
        return gameboxClassLoader;
    }

    private void initializeDeployedGameRepository() {
        deployedGameRepository = new GameRepositoryImpl(new GameIdGeneratorImpl());
    }

    private void initializeLoadedGameRepository() {
        instantiatedGameRepository = new GameRepositoryImpl();
    }

    private void initializeDeploymentRepository() {
        deploymentRepository = new DeploymentRepositoryImpl();
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
        gameService = new GameServiceImpl(deployedGameRepository, instantiatedGameRepository, gameDeployer, gameLoader,
                deploymentRepository, gameInstantiator);
    }

    private void initializeDeploymentService() {
        deploymentService = new DeploymentServiceImpl(deploymentRepository);
    }

    private void initializeGameInstantiator() {
        gameInstantiator = new DefaultGameInstantiator();
    }

    private void initializeGameboxClassLoader() {
        gameboxClassLoader = new GameboxClassLoader();
    }

    private static class InstanceHolder {
        public static final GameboxContext INSTANCE = new GameboxContext();
    }

}
