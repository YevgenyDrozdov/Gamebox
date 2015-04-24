package com.epam.jmp.gamebox.deploy.meta;


import com.epam.jmp.gamebox.DeploymentId;
import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.GameManifest;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;

public class ManifestBasedGameDescriptor implements GameDescriptor {

    private GameManifest manifest;
    private DeploymentDescriptor deploymentDescriptor;

    public ManifestBasedGameDescriptor(GameManifest manifest) {
        this.manifest = manifest;
    }

    @Override
    public String getGameName() {
        return manifest.getGameName();
    }

    @Override
    public String getGameVersion() {
        return manifest.getGameVersion();
    }

    @Override
    public String getControllerClass() {
        return manifest.getControllerClass();
    }

    @Override
    public String getMiniaturePath() {
        return manifest.getMiniaturePath();
    }

    @Override
    public DeploymentDescriptor getDeploymentDescriptor() {
        return deploymentDescriptor;
    }

    public void setDeploymentDescriptor(DeploymentDescriptor deploymentDescriptor) {
        this.deploymentDescriptor = deploymentDescriptor;
    }
}
