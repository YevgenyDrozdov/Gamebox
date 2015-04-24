package com.epam.jmp.gamebox.deploy;

import com.epam.jmp.gamebox.GameDescriptor;

public interface DeployAssistant<T extends GameDistributionItem> {

    DeploymentDescriptor deploy(T distributionItem);

}
