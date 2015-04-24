package com.epam.jmp.gamebox.deploy;

import java.util.List;

public interface GameDeployer {

    void init(GameDeployerConfiguration configuration, GameDistributionItemFactory distributionItemFactory);

    List<DeploymentDescriptor> refreshDeployments();
    void undeploy(DeploymentDescriptor deploymentDescriptor);

}
