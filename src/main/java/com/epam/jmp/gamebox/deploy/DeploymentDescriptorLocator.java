package com.epam.jmp.gamebox.deploy;

public interface DeploymentDescriptorLocator<T, E extends GameDistributionItem> {

    T getDeploymentDescriptorLocation(E item);

}
