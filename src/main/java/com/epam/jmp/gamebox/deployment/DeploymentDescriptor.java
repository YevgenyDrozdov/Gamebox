package com.epam.jmp.gamebox.deployment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deployment")
public class DeploymentDescriptor {

    private String gameId;
    private String deploymentTime;

    @XmlElement(name = "gameId")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @XmlElement(name = "time")
    public String getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(String deploymentTime) {
        this.deploymentTime = deploymentTime;
    }
}
