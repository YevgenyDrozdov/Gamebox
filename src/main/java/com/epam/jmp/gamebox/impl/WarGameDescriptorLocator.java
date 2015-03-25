package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.metainformation.ExtensibleGameDescriptor;

import java.io.File;

public abstract class WarGameDescriptorLocator {

    public abstract GameDescriptor extractGameDescriptor(File unpackedWar);

}
