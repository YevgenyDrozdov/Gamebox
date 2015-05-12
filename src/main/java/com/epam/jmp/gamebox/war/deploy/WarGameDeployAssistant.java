package com.epam.jmp.gamebox.war.deploy;

import com.epam.jmp.gamebox.deploy.DeploymentId;
import com.epam.jmp.gamebox.deploy.DeployAssistant;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
import com.epam.jmp.gamebox.deploy.XmlDeploymentDescriptor;
import com.epam.jmp.gamebox.util.FileUtils;
import com.epam.jmp.gamebox.util.XmlUtils;

import java.io.File;

public class WarGameDeployAssistant implements DeployAssistant<WarGameDistributionItem> {

    private WarXmlDeploymentDescriptorLocator deploymentDescriptorLocator;

    public WarGameDeployAssistant(WarXmlDeploymentDescriptorLocator deploymentDescriptorLocator) {
        this.deploymentDescriptorLocator = deploymentDescriptorLocator;
    }

    @Override
    public DeploymentDescriptor deploy(WarGameDistributionItem distributionItem) {

        File warFile = distributionItem.getItem();
        File unpackedWar = new File(warFile.getParent(), FileUtils.trimExtension(warFile.getName()));

        WarDeploymentDescriptor deploymentDescriptor = null;

        if (!deploymentDescriptorExists(distributionItem)) {
            FileUtils.unzip(warFile, unpackedWar);
            deploymentDescriptor = createDeploymentDescriptor(distributionItem);
        } else {
            deploymentDescriptor = readDeploymentDescriptor(distributionItem);
            if (deploymentDescriptor.getDeploymentTime() != distributionItem.getItem().lastModified()) {
                FileUtils.removeDirectory(unpackedWar);
                FileUtils.unzip(warFile, unpackedWar);
                deploymentDescriptor = createDeploymentDescriptor(distributionItem);
            }
        }

        deploymentDescriptor.setDeploymentId(new DeploymentId(Long.toString(deploymentDescriptor.getDeploymentTime())));
        deploymentDescriptor.setClassLoader(new WarGameClassLoaderBuilder(unpackedWar).build());
        deploymentDescriptor.setUnpackedWar(unpackedWar.getAbsolutePath());

        return deploymentDescriptor;
    }

    private WarDeploymentDescriptor createDeploymentDescriptor(WarGameDistributionItem distributionItem) {
        XmlDeploymentDescriptor deploymentDescriptor = new XmlDeploymentDescriptor();

        deploymentDescriptor.setDeploymentTime(distributionItem.getItem().lastModified());

        File deploymentDescriptorLocation = deploymentDescriptorLocator.getDeploymentDescriptorLocation(distributionItem);
        XmlUtils.marshalXml(deploymentDescriptorLocation, XmlDeploymentDescriptor.class, deploymentDescriptor);

        return new WarDeploymentDescriptor(deploymentDescriptor);
    }

    private WarDeploymentDescriptor readDeploymentDescriptor(WarGameDistributionItem distributionItem) {
        File deploymentDescriptorLocation = deploymentDescriptorLocator.getDeploymentDescriptorLocation(distributionItem);
        DeploymentDescriptor xmlDeploymentDescriptor = XmlUtils.unmarshalXml(deploymentDescriptorLocation, XmlDeploymentDescriptor.class);
        return new WarDeploymentDescriptor(xmlDeploymentDescriptor);
    }

    private boolean deploymentDescriptorExists(WarGameDistributionItem distributionItem) {
        File deploymentDescriptorLocation = deploymentDescriptorLocator.getDeploymentDescriptorLocation(distributionItem);
        return deploymentDescriptorLocation.exists();
    }

}
