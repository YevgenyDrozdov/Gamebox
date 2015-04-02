package com.epam.jmp.gamebox.deploy.meta;

import com.epam.jmp.gamebox.GameDescriptor;

public class ExtensibleGameDescriptor implements GameDescriptor {

    private GameDescriptor wrappedGameDescriptor;

    protected ExtensibleGameDescriptor(GameDescriptor wrappedGameDescriptor) {
        this.wrappedGameDescriptor = wrappedGameDescriptor;
    }

    @Override
    public String getGameName() {
        return wrappedGameDescriptor.getGameName();
    }

    @Override
    public String getGameVersion() {
        return wrappedGameDescriptor.getGameVersion();
    }

    @Override
    public String getControllerClass() {
        return wrappedGameDescriptor.getControllerClass();
    }

    @Override
    public ClassLoader getGameClassLoader() {
        return wrappedGameDescriptor.getGameClassLoader();
    }

    @Override
    public void setGameClassLoader(ClassLoader classLoader) {
        wrappedGameDescriptor.setGameClassLoader(classLoader);
    }

    protected GameDescriptor getWrappedGameDescriptor() {
        return wrappedGameDescriptor;
    }

}
