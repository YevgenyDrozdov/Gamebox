package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.metainformation.XmlManifestGameDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class WarXmlManifestGameDescriptorLocator extends WarGameDescriptorLocator {

    @Override
    public GameDescriptor extractGameDescriptor(File unpackedWar) {

        try {
            File xmlManifestFile = new File(unpackedWar.getAbsolutePath(),
                    File.separator + "META-INF" + File.separator + "manifest.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(XmlManifestGameDescriptor.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            XmlManifestGameDescriptor xmlManifest = (XmlManifestGameDescriptor)unmarshaller.unmarshal(xmlManifestFile);

            WarGameDescriptor warXmlManifest = new WarGameDescriptor(xmlManifest);
            warXmlManifest.setUnpackedWar(unpackedWar.getAbsolutePath());

            return warXmlManifest;

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }



}
