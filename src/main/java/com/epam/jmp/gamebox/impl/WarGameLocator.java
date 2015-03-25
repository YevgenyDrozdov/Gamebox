package com.epam.jmp.gamebox.impl;

import java.io.File;

public class WarGameLocator extends FileSystemGameLocator {

    @Override
    public boolean isGame(File item) {
        return item.isFile() && item.getName().endsWith(".war");
    }

}
