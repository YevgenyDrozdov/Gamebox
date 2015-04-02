package com.epam.jmp.gamebox;

public interface GameDescriptor extends GameManifest {

    ClassLoader getGameClassLoader();
    void setGameClassLoader(ClassLoader classLoader);

}
