package com.epam.jmp.gamebox.loader;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.deploy.meta.GameDescriptorImpl;
import java.util.ArrayList;

public class GameDescriptorLoadHelper {

    private ArrayList<GameDescriptorLoadAssistant> manifestLoadAssistants;

    public GameDescriptorLoadHelper() {
        manifestLoadAssistants = new ArrayList<GameDescriptorLoadAssistant>();
    }

    public GameDescriptor loadGameDescriptor() {
        GameDescriptor initialDescriptor = new GameDescriptorImpl();

        for (GameDescriptorLoadAssistant assistant : manifestLoadAssistants) {
            initialDescriptor = assistant.loadDescriptorAttributes(initialDescriptor);
        }

        return initialDescriptor;
    }

    public GameDescriptorLoadHelper addLoadAssistant(GameDescriptorLoadAssistant assistant) {
        manifestLoadAssistants.add(assistant);
        return this;
    }

}
