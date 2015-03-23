package com.epam.jmp.gamebox;

public interface GameBuilder<T> {

    Game buildGame(T item);

}
