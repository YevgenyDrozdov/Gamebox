package com.epam.jmp.gamebox.instantiator;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameController;
import com.epam.jmp.gamebox.impl.GameImpl;

public class DefaultGameInstantiator implements GameInstantiator {

    @Override
    public Game instantiate(Game game) {

        ClassLoader gameClassLoader = game.getDescriptor().getDeploymentDescriptor().getClassLoader();
        String controllerClassName = game.getDescriptor().getControllerClass();

        try {
            Class controllerClass = Class.forName(controllerClassName, true, gameClassLoader);
            GameController gameController = (GameController) controllerClass.newInstance();

            ((GameImpl)game).setController(gameController);

        } catch (Exception e) {
            throw new RuntimeException("Cannot instantiate game controller.", e);
        }

        return game;
    }

}
