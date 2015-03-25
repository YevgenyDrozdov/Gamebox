package com.epam.jmp.gamebox;

public interface GameLoader<T> {

    Game loadGame(GameDescriptor descriptor);
    GameDescriptor getGameDescriptor(T item);

}
