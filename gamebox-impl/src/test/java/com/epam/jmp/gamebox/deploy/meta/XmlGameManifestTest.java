package com.epam.jmp.gamebox.deploy.meta;

import com.epam.jmp.gamebox.util.XmlUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlGameManifestTest {

    private File folderWithManifest;
    private File manifestFile;

    @Before
    public void init() {
        String manifestFileLocation = getClass().getResource("/manifest.xml").getFile();
        manifestFile = new File(manifestFileLocation);
        folderWithManifest = manifestFile.getParentFile();
    }

    @Test
    public void testMarshal() {
        XmlGameManifest gameManifest = new XmlGameManifest();

        List<String> jsResources = new ArrayList<>();
        jsResources.add("/js1.js");
        jsResources.add("/js2.js");
        gameManifest.setJsResources(jsResources);

        XmlUtils.marshalXml(new File(folderWithManifest, "testManifestMarshalled.xml"), XmlGameManifest.class, gameManifest);
    }

    @Test
    public void testUnmarshal() {
        XmlGameManifest gameManifest = XmlUtils.unmarshalXml(manifestFile, XmlGameManifest.class);
        Assert.assertTrue(gameManifest.getJsResources() != null);
    }

}
