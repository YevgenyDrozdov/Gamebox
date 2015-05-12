package com.epam.jmp.gamebox.war.loader;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
import com.epam.jmp.gamebox.war.deploy.WarGameDeployAssistant;
import com.epam.jmp.gamebox.war.deploy.WarGameDistributionItem;
import com.epam.jmp.gamebox.war.deploy.WarXmlDeploymentDescriptorLocator;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class WarGameLoaderTest {

    private WarGameDistributionItem distributionItem;
    private DeploymentDescriptor deploymentDescriptor;

    @Before
    public void init() {
        File warFile = new File(getClass().getResource("/games-war/snake.war").getFile());
        distributionItem = new WarGameDistributionItem(warFile);

        WarXmlDeploymentDescriptorLocator xmlDeploymentDescriptorLocator = new WarXmlDeploymentDescriptorLocator();
        WarGameDeployAssistant assistant = new WarGameDeployAssistant(xmlDeploymentDescriptorLocator);

        deploymentDescriptor = assistant.deploy(distributionItem);
    }

    @Test
    public void testLoadGame() {
        WarGameLoader loader = new WarGameLoader();
        Game game = loader.loadGame(deploymentDescriptor);

        Assert.assertTrue("Game descriptor must not be null.", game.getDescriptor() != null);
        Assert.assertTrue("Game name must not be null.", game.getDescriptor().getGameName() != null);
        Assert.assertTrue("Game version must not be null", game.getDescriptor().getGameVersion() != null);
        Assert.assertTrue("Game controller class must not be null.", game.getDescriptor().getControllerClass() != null);
        Assert.assertTrue("Game deployment descriptor must not be null.", game.getDescriptor().getDeploymentDescriptor() != null);

        DeploymentDescriptor loadedGameDeploymentDescriptor = game.getDescriptor().getDeploymentDescriptor();

        Assert.assertTrue("Deployment descriptor of the game must be equal to original deployment descriptor.",
                deploymentDescriptor.getDeploymentId().getId().equals(loadedGameDeploymentDescriptor.getDeploymentId().getId())
                    && deploymentDescriptor.getDeploymentTime() == loadedGameDeploymentDescriptor.getDeploymentTime()
                    && deploymentDescriptor.getClassLoader() == loadedGameDeploymentDescriptor.getClassLoader());

    }

}
