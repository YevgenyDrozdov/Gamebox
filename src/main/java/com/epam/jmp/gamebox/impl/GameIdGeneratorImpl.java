package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameIdGenerator;

public class GameIdGeneratorImpl implements GameIdGenerator {

    @Override
    public String generateId(Game game) {
        return game.getDescriptor().getGameName() + ":" + game.getDescriptor().getGameVersion() + ":"
                + game.getDescriptor().getDeploymentDescriptor().getDeploymentId().getId();
    }

}
