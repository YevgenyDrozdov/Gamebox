package com.epam.jmp.gamebox.deploy.meta;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;

public class GameDescriptorImpl implements GameDescriptor {

    private GameDescriptor parent;
    private String gameName;
    private String gameVersion;
    private String controllerClass;
    private String miniaturePath;
    private DeploymentDescriptor deploymentDescriptor;

    public GameDescriptorImpl() {
        parent = new EmptyParent();
    }

    public GameDescriptorImpl(GameDescriptor parent) {
        this.parent = parent;
    }

    @Override
    public DeploymentDescriptor getDeploymentDescriptor() {
        if (deploymentDescriptor != null) {
            return deploymentDescriptor;
        }

        return parent.getDeploymentDescriptor();
    }

    @Override
    public String getGameName() {
        if (gameName != null) {
            return gameName;
        }

        return parent.getGameName();
    }

    @Override
    public String getGameVersion() {
        if (gameVersion != null) {
            return gameVersion;
        }

        return parent.getGameVersion();
    }

    @Override
    public String getControllerClass() {
        if (controllerClass != null) {
            return controllerClass;
        }

        return parent.getControllerClass();
    }

    @Override
    public String getMiniaturePath() {
        if (miniaturePath != null) {
            return miniaturePath;
        }

        return parent.getMiniaturePath();
    }

    public void setParent(GameDescriptor parent) {
        this.parent = parent;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public void setControllerClass(String controllerClass) {
        this.controllerClass = controllerClass;
    }

    public void setMiniaturePath(String miniaturePath) {
        this.miniaturePath = miniaturePath;
    }

    public void setDeploymentDescriptor(DeploymentDescriptor deploymentDescriptor) {
        this.deploymentDescriptor = deploymentDescriptor;
    }

    private static class EmptyParent implements GameDescriptor {
        @Override
        public DeploymentDescriptor getDeploymentDescriptor() {
            return null;
        }

        @Override
        public String getGameName() {
            return null;
        }

        @Override
        public String getGameVersion() {
            return null;
        }

        @Override
        public String getControllerClass() {
            return null;
        }

        @Override
        public String getMiniaturePath() {
            return null;
        }
    }
}
