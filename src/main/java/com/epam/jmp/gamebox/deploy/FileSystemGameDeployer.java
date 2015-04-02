package com.epam.jmp.gamebox.deploy;

import com.epam.jmp.gamebox.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileSystemGameDeployer implements GameDeployer {

    public static final String PROPERTY_NAME_REPOSITORY_PATH = "FileSystemGameDeployer.RepositoryPath";

    private GameDeployerConfiguration deployerConfiguration;
    private FileSystemGameDistributionItemFactory gameDistributionItemFactory;

    private Map<GameDistributionType, DeployAssistant> deployAssistants;

    @Override
    public void init(GameDeployerConfiguration configuration, GameDistributionItemFactory distributionItemFactory) {
        deployerConfiguration = configuration;
        gameDistributionItemFactory = (FileSystemGameDistributionItemFactory)distributionItemFactory;
    }

    @Override
    public List<GameDescriptor> deployAllGames() {

        List<GameDescriptor> gameDescriptors = new ArrayList<GameDescriptor>();

        File gamesRoot = getRepositoryFileSystemPath();
        File[] files = gamesRoot.listFiles();

        for (File file : files) {
            GameDistributionItem distributionItem = gameDistributionItemFactory.createGameDistributionItem(file);
            if (distributionItem != null) {
                DeployAssistant deployAssistant = deployAssistants.get(distributionItem.getDistributionType());
                gameDescriptors.add(deployAssistant.deploy(distributionItem));
            }
        }

        return gameDescriptors;
    }

    public Map<GameDistributionType, DeployAssistant> getDeployAssistants() {
        return deployAssistants;
    }

    public void setDeployAssistants(Map<GameDistributionType, DeployAssistant> deployAssistants) {
        this.deployAssistants = deployAssistants;
    }

    protected File getRepositoryFileSystemPath() {
        String repositoryPath = getDeployerConfiguration().getParameter(PROPERTY_NAME_REPOSITORY_PATH);
        if (repositoryPath != null) {
            return new File(repositoryPath);
        }

        throw new RuntimeException("The path to the repository is not set.");
    }

    protected GameDeployerConfiguration getDeployerConfiguration() {
        return deployerConfiguration;
    }

}
