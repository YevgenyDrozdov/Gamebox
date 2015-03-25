package com.epam.jmp.gamebox.metainformation;

import com.epam.jmp.gamebox.GameDescriptor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "manifest")
public class XmlManifestGameDescriptor implements GameDescriptor {

    private String gameName;
    private String gameVersion;
    private String controllerClass;

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @XmlElement(name = "game-name")
    public String getGameName() {
        return gameName;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    @XmlElement(name = "game-version")
    public String getGameVersion() {
        return gameVersion;
    }

    public void setControllerClass(String controllerClass) {
        this.controllerClass = controllerClass;
    }

    @XmlElement(name = "controller-class")
    public String getControllerClass() {
        return controllerClass;
    }
}
