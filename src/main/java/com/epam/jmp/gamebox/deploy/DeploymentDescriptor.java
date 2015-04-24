package com.epam.jmp.gamebox.deploy;

import com.epam.jmp.gamebox.DeploymentId;

public interface DeploymentDescriptor {

    Long getDeploymentTime();
    void setDeploymentTime(Long deploymentTime);

    DeploymentId getDeploymentId();
    void setDeploymentId(DeploymentId deploymentId);

    ClassLoader getGameClassLoader();
    void setGameClassLoader(ClassLoader classLoader);

}
