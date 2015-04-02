package com.epam.jmp.gamebox.deploy.meta;

import com.epam.jmp.gamebox.GameManifest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "manifest")
public class XmlGameManifest implements GameManifest {

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
