package com.epam.jmp.gamebox;

import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;

public interface GameLoader {

    Game loadGame(DeploymentDescriptor descriptor);

}
