package com.epam.jmp.gamebox.deploy;

import com.epam.jmp.gamebox.GameDescriptor;

public interface DeployAssistant<T extends GameDistributionItem> {

    GameDescriptor deploy(T distributionItem);

}
