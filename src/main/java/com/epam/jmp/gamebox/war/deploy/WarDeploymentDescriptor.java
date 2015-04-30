package com.epam.jmp.gamebox.war.deploy;

import com.epam.jmp.gamebox.DeploymentId;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;

public class WarDeploymentDescriptor implements DeploymentDescriptor {

    private String unpackedWar;
    private DeploymentDescriptor wrappedDeploymentDescriptor;

    public WarDeploymentDescriptor(DeploymentDescriptor wrappedDeploymentDescriptor) {
        this.wrappedDeploymentDescriptor = wrappedDeploymentDescriptor;
    }

    @Override
    public Long getDeploymentTime() {
        return getWrappedDeploymentDescriptor().getDeploymentTime();
    }

    @Override
    public void setDeploymentTime(Long deploymentTime) {
        getWrappedDeploymentDescriptor().setDeploymentTime(deploymentTime);
    }

    @Override
    public DeploymentId getDeploymentId() {
        return getWrappedDeploymentDescriptor().getDeploymentId();
    }

    @Override
    public void setDeploymentId(DeploymentId deploymentId) {
        getWrappedDeploymentDescriptor().setDeploymentId(deploymentId);
    }

    @Override
    public ClassLoader getClassLoader() {
        return getWrappedDeploymentDescriptor().getClassLoader();
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        getWrappedDeploymentDescriptor().setClassLoader(classLoader);
    }

    public void setUnpackedWar(String unpackedWar) {
        this.unpackedWar = unpackedWar;
    }

    public String getUnpackedWar() {
        return unpackedWar;
    }

    public DeploymentDescriptor getWrappedDeploymentDescriptor() {
        return wrappedDeploymentDescriptor;
    }
}
