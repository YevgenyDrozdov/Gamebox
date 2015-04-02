package com.epam.jmp.gamebox.war.deploy;

import com.epam.jmp.gamebox.deploy.DeploymentDescriptorLocator;
import java.io.File;

public class WarXmlDeploymentDescriptorLocator implements DeploymentDescriptorLocator<File, WarGameDistributionItem> {

    @Override
    public File getDeploymentDescriptorLocation(WarGameDistributionItem item) {

        File warFile = item.getItem();
        File parent = warFile.getParentFile();

        return new File(parent, warFile.getName() + ".deployed");
    }
}
