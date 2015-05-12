package com.epam.jmp.gamebox.deploy;

import com.epam.jmp.gamebox.war.deploy.WarGameDistributionItem;

import java.io.File;

public class FileSystemGameDistributionItemFactory implements GameDistributionItemFactory<File, GameDistributionItem<File>> {

    @Override
    public GameDistributionItem<File> createGameDistributionItem(File item) {

        if (item.getName().endsWith(".war")) {
            return new WarGameDistributionItem(item);
        }

        return null;
    }

}
