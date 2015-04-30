package com.epam.jmp.gamebox.loader;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.deploy.meta.ManifestBasedGameDescriptor;
import com.epam.jmp.gamebox.deploy.meta.XmlGameManifest;
import com.epam.jmp.gamebox.util.XmlUtils;

import java.io.InputStream;

public class XmlManifestGameDescriptorLoadAssistant implements GameDescriptorLoadAssistant {

    private InputStream xmlManifestInputStream;

    public XmlManifestGameDescriptorLoadAssistant(InputStream xmlInputStream) {
        xmlManifestInputStream = xmlInputStream;
    }

    @Override
    public GameDescriptor loadDescriptorAttributes(GameDescriptor descriptor) {
        XmlGameManifest gameManifest = XmlUtils.unmarshalXml(xmlManifestInputStream, XmlGameManifest.class);
        ManifestBasedGameDescriptor gameDescriptor = new ManifestBasedGameDescriptor(descriptor, gameManifest);
        return gameDescriptor;
    }

}
