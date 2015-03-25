package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameController;
import com.epam.jmp.gamebox.GameDescriptor;

public class GameImpl implements Game {

    private GameController controller;
    private GameDescriptor descriptor;

    @Override
    public GameController getController() {
        return controller;
    }

    @Override
    public GameDescriptor getDescriptor() {
        return descriptor;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setDescriptor(GameDescriptor descriptor) {
        this.descriptor = descriptor;
    }
}
