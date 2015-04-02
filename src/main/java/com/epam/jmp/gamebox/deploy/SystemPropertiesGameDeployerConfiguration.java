package com.epam.jmp.gamebox.deploy;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SystemPropertiesGameDeployerConfiguration implements GameDeployerConfiguration {

    public static final String SYSTEM_PROPERTY_PREFIX_GAME_REPOSITORY_PROPERTIES = "GR.";

    @Override
    public List<String> getParametersNames() {
        List<String> result = new ArrayList<String>();

        Properties systemProperties = System.getProperties();

        for (Object propertyNameObj : systemProperties.keySet()) {
            String propertyName = propertyNameObj.toString();

            if (propertyName.startsWith(SYSTEM_PROPERTY_PREFIX_GAME_REPOSITORY_PROPERTIES)) {
                result.add(propertyName.substring(SYSTEM_PROPERTY_PREFIX_GAME_REPOSITORY_PROPERTIES.length()));
            }
        }

        return result;
    }

    @Override
    public String getParameter(String name) {
        return System.getProperty(SYSTEM_PROPERTY_PREFIX_GAME_REPOSITORY_PROPERTIES + name);
    }

}
