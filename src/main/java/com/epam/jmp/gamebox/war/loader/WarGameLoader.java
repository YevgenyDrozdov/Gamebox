package com.epam.jmp.gamebox.war.loader;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameLoader;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
import com.epam.jmp.gamebox.deploy.meta.ManifestBasedGameDescriptor;
import com.epam.jmp.gamebox.deploy.meta.XmlGameManifest;
import com.epam.jmp.gamebox.impl.GameImpl;
import com.epam.jmp.gamebox.util.FileUtils;
import com.epam.jmp.gamebox.war.deploy.WarDeploymentDescriptor;

import java.io.File;

public class WarGameLoader implements GameLoader {

    @Override
    public Game loadGame(DeploymentDescriptor descriptor) {

        if (descriptor instanceof WarDeploymentDescriptor) {
            WarDeploymentDescriptor warDeploymentDescriptor = (WarDeploymentDescriptor) descriptor;

            File unpackedWar = new File(warDeploymentDescriptor.getUnpackedWar());
            File manifestFile = FileUtils.getChild(unpackedWar, "\\META-INF\\manifest.xml");

            XmlGameManifest gameManifest = FileUtils.unmarshalXml(manifestFile, XmlGameManifest.class);
            ManifestBasedGameDescriptor gameDescriptor = new ManifestBasedGameDescriptor(gameManifest);
            gameDescriptor.setDeploymentDescriptor(descriptor);

            GameImpl game = new GameImpl();
            game.setDescriptor(gameDescriptor);

            return game;
        }

        /*ClassLoader gameClassLoader = descriptor.getGameClassLoader();
        String controllerClassName = descriptor.getControllerClass();

        try {
            Class controllerClass = Class.forName(controllerClassName, true, gameClassLoader);

            GameController controller = (GameController)controllerClass.newInstance();

            GameImpl game = new GameImpl();
            game.setController(controller);
            game.setDescriptor(descriptor);

            return game;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/

        throw new RuntimeException("Could not load the game");
    }

}
