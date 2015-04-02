package com.epam.jmp.gamebox.deploy.meta;


import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.GameManifest;

public class ManifestBasedGameDescriptor implements GameDescriptor {

    private GameManifest manifest;
    private ClassLoader classLoader;

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
    public ClassLoader getGameClassLoader() {
        return classLoader;
    }

    public void setGameClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
