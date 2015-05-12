package com.epam.jmp.gamebox.war.deploy;

import com.epam.jmp.gamebox.deploy.GameDistributionItem;
import com.epam.jmp.gamebox.deploy.GameDistributionType;

import java.io.File;

public class WarGameDistributionItem implements GameDistributionItem<File> {

    private File item;

    public WarGameDistributionItem(File item) {
        this.item = item;
    }

    @Override
    public GameDistributionType getDistributionType() {
        return GameDistributionType.WAR;
    }

    @Override
    public File getItem() {
        return item;
    }

}
