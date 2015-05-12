package com.epam.jmp.gamebox.deploy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deployment")
public class XmlDeploymentDescriptor implements DeploymentDescriptor {

    private Long deploymentTime;
    private ClassLoader classloader;
    private DeploymentId deploymentId;

    @XmlElement(name = "time", type = Long.class)
    public Long getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Long deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    @Override
    public DeploymentId getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(DeploymentId deploymentId) {
        this.deploymentId = deploymentId;
    }

    @Override
    public ClassLoader getClassLoader() {
        return classloader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classloader = classLoader;
    }
}
