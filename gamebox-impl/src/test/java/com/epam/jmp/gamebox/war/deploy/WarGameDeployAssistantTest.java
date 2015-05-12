package com.epam.jmp.gamebox.war.deploy;

import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
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

        DeploymentDescriptor descriptor = assistant.deploy(distributionItem);

        Assert.assertTrue(descriptor.getDeploymentId() != null);
        Assert.assertTrue(descriptor.getDeploymentTime() != null);
        Assert.assertTrue(descriptor.getClassLoader() != null);
    }

}
