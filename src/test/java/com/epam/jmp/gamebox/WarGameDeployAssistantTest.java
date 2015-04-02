package com.epam.jmp.gamebox;

import com.epam.jmp.gamebox.war.deploy.WarGameDeployAssistant;
import com.epam.jmp.gamebox.war.deploy.WarGameDistributionItem;
import com.epam.jmp.gamebox.war.deploy.WarXmlDeploymentDescriptorLocator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class WarGameDeployAssistantTest {

    private WarGameDistributionItem distributionItem;

    @Before
    public void init() {
        File warFile = new File(getClass().getResource("/games-war/snake.war").getFile());
        distributionItem = new WarGameDistributionItem(warFile);
    }

    @Test
    public void testDeploy() {
        WarXmlDeploymentDescriptorLocator xmlDeploymentDescriptorLocator = new WarXmlDeploymentDescriptorLocator();
        WarGameDeployAssistant assistant = new WarGameDeployAssistant(xmlDeploymentDescriptorLocator);

        GameDescriptor descriptor = assistant.deploy(distributionItem);

        Assert.assertTrue(descriptor.getGameName() != null);
        Assert.assertTrue(descriptor.getGameVersion() != null);
        Assert.assertTrue(descriptor.getControllerClass() != null);
    }

}
