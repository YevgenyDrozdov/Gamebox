package com.epam.jmp.gamebox;

import com.epam.jmp.gamebox.impl.WarGameLoader;
import com.epam.jmp.gamebox.impl.WarXmlManifestGameDescriptorLocator;
import com.epam.jmp.gamebox.impl.WarGameDescriptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class WarGameLoaderTest {

    private File warFile;
    private WarGameLoader gameLoader;

    @Before
    public void init() {
        URL warURL = getClass().getResource("/games-war/snake.war");
        warFile = new File(warURL.getFile());

        gameLoader = new WarGameLoader();

        WarXmlManifestGameDescriptorLocator gameDescriptorLocator = new WarXmlManifestGameDescriptorLocator();
        gameLoader.setGameDescriptorLocator(gameDescriptorLocator);
    }

    @Test
    public void testGetDescriptor() {
        GameDescriptor descriptor = gameLoader.getGameDescriptor(warFile);

        Assert.assertTrue(descriptor instanceof WarGameDescriptor);
    }

    @Test
    public void testLoadGame() {

        GameDescriptor descriptor = gameLoader.getGameDescriptor(warFile);

        Game game = gameLoader.loadGame(descriptor);
        Assert.assertTrue("Game was loaded by the same classloader.",
                game.getController().getClass().getClassLoader() != this.getClass().getClassLoader());
    }

}
