package com.epam.jmp.gamebox.deploy.meta;

import com.epam.jmp.gamebox.GameDescriptor;

public class ManifestBasedGameDescriptor extends GameDescriptorImpl {

    public ManifestBasedGameDescriptor(GameManifest manifest) {
        initializeFromManifest(manifest);
    }

    public ManifestBasedGameDescriptor(GameDescriptor parentGameDescriptor, GameManifest manifest) {
        super(parentGameDescriptor);
        initializeFromManifest(manifest);
    }

    protected void initializeFromManifest(GameManifest manifest) {
        setGameName(manifest.getGameName());
        setGameVersion(manifest.getGameVersion());
        setControllerClass(manifest.getControllerClass());
        setMiniaturePath(manifest.getMiniaturePath());
        setJsResources(manifest.getJsResources());
        setCssResources(manifest.getCssResources());
    }

}
