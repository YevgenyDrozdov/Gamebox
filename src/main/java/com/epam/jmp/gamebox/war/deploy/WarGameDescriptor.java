package com.epam.jmp.gamebox.war.deploy;

import com.epam.jmp.gamebox.GameManifest;
import com.epam.jmp.gamebox.deploy.meta.ExtensibleGameDescriptor;
import com.epam.jmp.gamebox.deploy.meta.ManifestBasedGameDescriptor;

public class WarGameDescriptor extends ExtensibleGameDescriptor {

    private String unpackedWar;

    public WarGameDescriptor(GameManifest manifest) {
        super(new ManifestBasedGameDescriptor(manifest));
    }

    public String getUnpackedWar() {
        return unpackedWar;
    }

    public void setUnpackedWar(String unpackedWar) {
        this.unpackedWar = unpackedWar;
    }

}
