package com.epam.jmp.gamebox.deploy;

public interface DeploymentDescriptor {

    Long getDeploymentTime();
    void setDeploymentTime(Long deploymentTime);

    DeploymentId getDeploymentId();
    void setDeploymentId(DeploymentId deploymentId);

    ClassLoader getClassLoader();
    void setClassLoader(ClassLoader classLoader);

}
