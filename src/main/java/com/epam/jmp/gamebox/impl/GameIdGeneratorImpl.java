package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.GameIdGenerator;

public class GameIdGeneratorImpl implements GameIdGenerator {

    @Override
    public String generateId(GameDescriptor descriptor) {
        return Long.toString(System.currentTimeMillis());
    }

}
