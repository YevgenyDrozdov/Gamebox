package com.epam.jmp.gamebox.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public final class FileUtils {
    private FileUtils() {
    }

    public static void removeDirectory(File directory) {
        directory.delete();
    }

    public static void unzip(File item, File destination) {
        try {
            ZipFile zipFile = new ZipFile(item);

            if (!destination.exists()) {
                destination.mkdir();
                zipFile.extractAll(destination.getAbsolutePath());
            }
        } catch (ZipException e) {
            throw new RuntimeException();
        }
    }

    public static String trimExtension(String fileName) {
        int dotPosition = fileName.lastIndexOf('.');
        if (dotPosition > 0) {
            return fileName.substring(0, dotPosition);
        }

        return fileName;
    }

    public static File getChild(File parent, String relativePath) {
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

    public static File findChild(File parent, final String childName) {
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
