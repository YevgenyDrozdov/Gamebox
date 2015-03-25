package com.epam.jmp.gamebox.metainformation;

import com.epam.jmp.gamebox.GameDescriptor;

public class ExtensibleGameDescriptor implements GameDescriptor {

    private GameDescriptor wrappedGameDescriptor;

    public ExtensibleGameDescriptor(GameDescriptor wrappedGameDescriptor) {
        this.wrappedGameDescriptor = wrappedGameDescriptor;
    }

    @Override
    public String getGameId() {
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

    protected GameDescriptor getWrappedGameDescriptor() {
        return wrappedGameDescriptor;
    }

}
