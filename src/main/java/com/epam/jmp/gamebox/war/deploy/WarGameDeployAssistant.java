package com.epam.jmp.gamebox.war.deploy;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.deploy.DeployAssistant;
import com.epam.jmp.gamebox.deploy.XmlDeploymentDescriptor;
import com.epam.jmp.gamebox.deploy.meta.XmlGameManifest;
import com.epam.jmp.gamebox.util.FileUtils;
import com.epam.jmp.gamebox.war.loader.WarGameClassLoaderBuilder;

import java.io.File;
import java.util.Calendar;

public class WarGameDeployAssistant implements DeployAssistant<WarGameDistributionItem> {

    private WarXmlDeploymentDescriptorLocator deploymentDescriptorLocator;

    public WarGameDeployAssistant(WarXmlDeploymentDescriptorLocator deploymentDescriptorLocator) {
        this.deploymentDescriptorLocator = deploymentDescriptorLocator;
    }

    @Override
    public GameDescriptor deploy(WarGameDistributionItem distributionItem) {

        File warFile = distributionItem.getItem();

        File unpackedWar = new File(warFile.getParent(), FileUtils.trimExtension(warFile.getName()));

        if (!deploymentDescriptorExists(distributionItem)) {
            FileUtils.unzip(warFile, unpackedWar);
            createDeploymentDescriptor(distributionItem);
        }

        File manifestFile = FileUtils.getChild(unpackedWar, "\\META-INF\\manifest.xml");

        XmlGameManifest gameManifest = FileUtils.unmarshalXml(manifestFile, XmlGameManifest.class);

        GameDescriptor descriptor = new WarGameDescriptor(gameManifest);
        descriptor.setGameClassLoader(new WarGameClassLoaderBuilder(unpackedWar).build());

        return descriptor;
    }

    private void createDeploymentDescriptor(WarGameDistributionItem distributionItem) {
        XmlDeploymentDescriptor deploymentDescriptor = new XmlDeploymentDescriptor();

        deploymentDescriptor.setDeploymentTime(Long.toString(Calendar.getInstance().getTimeInMillis()));

        File deploymentDescriptorLocation = deploymentDescriptorLocator.getDeploymentDescriptorLocation(distributionItem);
        FileUtils.marshalXml(deploymentDescriptorLocation, XmlDeploymentDescriptor.class, deploymentDescriptor);
    }

    private boolean deploymentDescriptorExists(WarGameDistributionItem distributionItem) {
        File deploymentDescriptorLocation = deploymentDescriptorLocator.getDeploymentDescriptorLocation(distributionItem);
        return deploymentDescriptorLocation.exists();
    }

}
