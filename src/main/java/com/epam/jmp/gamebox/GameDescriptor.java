package com.epam.jmp.gamebox;

import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;

public interface GameDescriptor extends GameManifest {

    DeploymentDescriptor getDeploymentDescriptor();

}
