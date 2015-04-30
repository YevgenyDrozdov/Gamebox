package com.epam.jmp.gamebox.war.loader;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.GameLoader;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
import com.epam.jmp.gamebox.deploy.meta.GameDescriptorImpl;
import com.epam.jmp.gamebox.impl.GameImpl;
import com.epam.jmp.gamebox.loader.AnnotationGameDescriptorLoadAssistant;
import com.epam.jmp.gamebox.loader.GameDescriptorLoadHelper;
import com.epam.jmp.gamebox.war.deploy.WarDeploymentDescriptor;

import java.io.File;

public class WarGameLoader implements GameLoader {

    public static final String MANIFEST_LOCATION = "\\META-INF\\manifest.xml";

    @Override
    public Game loadGame(DeploymentDescriptor descriptor) {

        if (descriptor instanceof WarDeploymentDescriptor) {
            WarDeploymentDescriptor warDeploymentDescriptor = (WarDeploymentDescriptor) descriptor;

            File unpackedWar = new File(warDeploymentDescriptor.getUnpackedWar());

            GameDescriptorImpl gameDescriptor = new GameDescriptorImpl(loadGameDescriptor(unpackedWar.getAbsolutePath(),
                    descriptor));
            gameDescriptor.setDeploymentDescriptor(descriptor);

            GameImpl game = new GameImpl();
            game.setDescriptor(gameDescriptor);

            return game;
        }

        /*ClassLoader gameClassLoader = descriptor.getClassLoader();
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

    protected GameDescriptor loadGameDescriptor(String unpackedWar, DeploymentDescriptor descriptor) {
        GameDescriptorLoadHelper loadHelper = new GameDescriptorLoadHelper();

        loadHelper.addLoadAssistant(new AnnotationGameDescriptorLoadAssistant(descriptor.getClassLoader()));

        loadHelper.addLoadAssistant(new WarXmlManifestLoadAssistantBuilder()
                .unpackedWarLocation(unpackedWar)
                .manifestRelativePath(MANIFEST_LOCATION)
                .build());

        return loadHelper.loadGameDescriptor();
    }

}
