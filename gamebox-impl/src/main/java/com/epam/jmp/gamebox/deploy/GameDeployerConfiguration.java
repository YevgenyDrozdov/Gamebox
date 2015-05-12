package com.epam.jmp.gamebox.deploy;

import java.util.List;

public interface GameDeployerConfiguration {

    List<String> getParametersNames();
    String getParameter(String name);

}
