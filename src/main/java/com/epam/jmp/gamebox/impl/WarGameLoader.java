package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameController;
import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.GameLoader;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarGameLoader implements GameLoader<File> {

    protected WarGameDescriptorLocator gameDescriptorLocator;

    @Override
    public Game loadGame(GameDescriptor descriptor) {

        if (!(descriptor instanceof WarGameDescriptor)) {
            throw new IllegalArgumentException("descriptor must be type of WarGameDescriptor");
        }

        WarGameDescriptor warGamedescriptor = (WarGameDescriptor)descriptor;

        File unpackedWar = new File(warGamedescriptor.getUnpackedWar());

        List<URL> gameBinariesPaths = new ArrayList<URL>();

        File libDirectory = getChild(unpackedWar, "\\WEB-INF\\lib");
        if (libDirectory != null) {

            File[] jarFiles = libDirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jar");
                }
            });

            for (File jarFile : jarFiles) {
                try {
                    gameBinariesPaths.add(jarFile.toURI().toURL());
                } catch (MalformedURLException e) {
                }
            }
        }

        File classesDirectory = getChild(unpackedWar, "\\WEB-INF\\classes");
        if (classesDirectory != null) {
            try {
                gameBinariesPaths.add(classesDirectory.toURI().toURL());
            } catch (MalformedURLException e) {
            }
        }

        ClassLoader gameClassloader = new URLClassLoader(gameBinariesPaths.toArray(new URL[0]));

        try {
            Class gameClass = Class.forName(warGamedescriptor.getControllerClass(), true, gameClassloader);
            GameController gameInstance = (GameController)gameClass.newInstance();

            GameImpl game = new GameImpl();
            game.setController(gameInstance);
            game.setDescriptor(descriptor);

            return game;

        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        return null;
    }

    @Override
    public GameDescriptor getGameDescriptor(File item) {
        File unpackedWar = unpackWar(item);
        return getGameDescriptorLocator().extractGameDescriptor(unpackedWar);
    }

    protected boolean isGameDeployed(File item) {
        File parent = item.getParentFile();

        String deployedGameMarkerName = item.getName() + ".deployed";
        File markerFile = new File(parent, deployedGameMarkerName);


    }

    public void setGameDescriptorLocator(WarGameDescriptorLocator gameDescriptorLocator) {
        this.gameDescriptorLocator = gameDescriptorLocator;
    }

    public WarGameDescriptorLocator getGameDescriptorLocator() {
        return gameDescriptorLocator;
    }

    private File unpackWar(File item) {
        File unpackedDirectory = null;

        try {
            ZipFile warFile = new ZipFile(item);

            String warFileName = item.getName();
            String unpackedDirectoryName = warFileName.substring(0, warFileName.length() - ".war".length());
            unpackedDirectory = new File(item.getParent(), unpackedDirectoryName);
            if (!unpackedDirectory.exists()) {
                unpackedDirectory.mkdir();
                warFile.extractAll(unpackedDirectory.getAbsolutePath());
            }
        } catch (ZipException e) {
            throw new RuntimeException();
        }

        return unpackedDirectory;
    }

    protected File getChild(File parent, String relativePath) {
        String[] childrenNames = relativePath.split("\\\\");

        if (childrenNames[0].isEmpty()) {
            childrenNames = Arrays.copyOfRange(childrenNames, 1, childrenNames.length);
        }

        File currentParent = parent;
        for (int i = 0; i < childrenNames.length; i++) {
            currentParent = findChild(currentParent, childrenNames[i]);
            if (currentParent == null) {
                return null;
            }
        }

        return currentParent;
    }

    protected File findChild(File parent, final String childName) {
        File[] result = parent.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return childName.equals(name);
            }

        });

        if (result.length > 0) {
            return result[0];
        }

        return null;
    }

}
