package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.metainformation.ExtensibleGameDescriptor;

public class WarGameDescriptor extends ExtensibleGameDescriptor {

    private String unpackedWar;

    public WarGameDescriptor(GameDescriptor wrappedGameDescriptor) {
        super(wrappedGameDescriptor);
    }

    public String getUnpackedWar() {
        return unpackedWar;
    }

    public void setUnpackedWar(String unpackedWar) {
        this.unpackedWar = unpackedWar;
    }

}
