package com.epam.jmp.gamebox.deploy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deployment")
public class XmlDeploymentDescriptor implements DeploymentDescriptor {

    private String deploymentTime;

    @XmlElement(name = "time")
    public String getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(String deploymentTime) {
        this.deploymentTime = deploymentTime;
    }
}
