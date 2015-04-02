package com.epam.jmp.gamebox.deploy;

import com.epam.jmp.gamebox.GameDescriptor;

import java.util.List;

public interface GameDeployer {

    void init(GameDeployerConfiguration configuration, GameDistributionItemFactory distributionItemFactory);
    List<GameDescriptor> deployAllGames();

}
