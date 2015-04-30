package com.epam.jmp.gamebox.war.loader;

import com.epam.jmp.gamebox.loader.XmlManifestGameDescriptorLoadAssistant;
import com.epam.jmp.gamebox.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class WarXmlManifestLoadAssistantBuilder {

    private String unpackedWarLocation;
    private String xmlManifestPath;

    public WarXmlManifestLoadAssistantBuilder unpackedWarLocation(String unpackedWarLocation) {
        this.unpackedWarLocation = unpackedWarLocation;
        return this;
    }

    public WarXmlManifestLoadAssistantBuilder manifestRelativePath(String xmlManifestPath) {
        this.xmlManifestPath = xmlManifestPath;
        return this;
    }

    public XmlManifestGameDescriptorLoadAssistant build() {
        File unpackedWar = new File(unpackedWarLocation);

        File xmlManifestFile = FileUtils.getChild(unpackedWar, xmlManifestPath);

        try {
            InputStream xmlFileInputStream = new FileInputStream(xmlManifestFile);
            XmlManifestGameDescriptorLoadAssistant assistant = new XmlManifestGameDescriptorLoadAssistant(xmlFileInputStream);
            return assistant;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not load XML manifest.");
        }
    }

}
