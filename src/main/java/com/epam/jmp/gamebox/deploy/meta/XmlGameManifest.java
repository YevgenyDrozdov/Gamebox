package com.epam.jmp.gamebox.deploy.meta;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "manifest")
public class XmlGameManifest implements GameManifest {

    private String gameName;
    private String gameVersion;
    private String controllerClass;
    private String miniaturePath;
    private List<String> jsResources;
    private List<String> cssResources;

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

    @XmlElement(name = "miniature-path")
    public String getMiniaturePath() {
        return miniaturePath;
    }

    public void setMiniaturePath(String miniaturePath) {
        this.miniaturePath = miniaturePath;
    }

    @XmlElementWrapper(name = "js-resources")
    @XmlElement(name = "js-resource")
    public List<String> getJsResources() {
        return jsResources;
    }

    public void setJsResources(List<String> jsResources) {
        this.jsResources = jsResources;
    }

    @XmlElementWrapper(name = "css-resources")
    @XmlElement(name = "css-resource")
    public List<String> getCssResources() {
        return cssResources;
    }

    public void setCssResources(List<String> cssResources) {
        this.cssResources = cssResources;
    }
}
