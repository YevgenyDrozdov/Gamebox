package com.epam.jmp.gamebox;

import java.net.URL;

public interface GameLocator<T> {

    boolean isGame(T item);
    URL getULRToGame(T item);

}
