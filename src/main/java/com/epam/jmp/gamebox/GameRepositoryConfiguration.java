package com.epam.jmp.gamebox;

import java.util.List;

public interface GameRepositoryConfiguration {

    List<String> getParametersNames();
    String getParameter(String name);

}
