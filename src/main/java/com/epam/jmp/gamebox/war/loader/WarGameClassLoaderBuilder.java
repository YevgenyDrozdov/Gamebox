package com.epam.jmp.gamebox.war.loader;

import com.epam.jmp.gamebox.util.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class WarGameClassLoaderBuilder {

    private File rootFolder;

    public WarGameClassLoaderBuilder(File rootFolder) {
        this.rootFolder = rootFolder;
    }

    public ClassLoader build() {
        List<URL> gameBinariesPaths = new ArrayList<URL>();

        try {
            gameBinariesPaths.add(rootFolder.toURI().toURL());
        } catch (MalformedURLException e) {
        }

        File libDirectory = FileUtils.getChild(rootFolder, "\\WEB-INF\\lib");
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

        File classesDirectory = FileUtils.getChild(rootFolder, "\\WEB-INF\\classes");
        if (classesDirectory != null) {
            try {
                gameBinariesPaths.add(classesDirectory.toURI().toURL());
            } catch (MalformedURLException e) {
            }
        }

        return new URLClassLoader(gameBinariesPaths.toArray(new URL[0]), this.getClass().getClassLoader());
    }

}
