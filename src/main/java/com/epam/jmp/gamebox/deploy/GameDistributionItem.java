package com.epam.jmp.gamebox.deploy;

public interface GameDistributionItem<T> {

    GameDistributionType getDistributionType();
    T getItem();

}
