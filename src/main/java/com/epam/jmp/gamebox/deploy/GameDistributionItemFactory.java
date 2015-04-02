package com.epam.jmp.gamebox.deploy;

public interface GameDistributionItemFactory<E, T extends GameDistributionItem<E>> {

    T createGameDistributionItem(E item);

}
