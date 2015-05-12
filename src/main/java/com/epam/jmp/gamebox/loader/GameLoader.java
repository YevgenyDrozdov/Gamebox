package com.epam.jmp.gamebox.loader;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;

public interface GameLoader {

    Game loadGame(DeploymentDescriptor descriptor);

}
