package com.epam.jmp.gamebox;

import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
import com.epam.jmp.gamebox.deploy.meta.GameManifest;

public interface GameDescriptor extends GameManifest {

    DeploymentDescriptor getDeploymentDescriptor();

}
