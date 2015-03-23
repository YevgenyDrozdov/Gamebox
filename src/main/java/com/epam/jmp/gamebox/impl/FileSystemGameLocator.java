package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.GameLocator;

import java.io.File;
import java.net.URL;

public class FileSystemGameLocator implements GameLocator<File> {

    @Override
    public boolean isGame(File item) {
        return false;
    }

    @Override
    public URL getULRToGame(File item) {
        return null;
    }

}
