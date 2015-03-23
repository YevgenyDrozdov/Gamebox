package com.epam.jmp.gamebox;

import com.epam.jmp.gamebox.impl.SystemPropertiesGameRepositoryConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class SystemPropertiesGameRepositoryConfigurationTest {

    private SystemPropertiesGameRepositoryConfiguration configuration;

    private static final Map<String, String> properties = new HashMap<String, String>() {
        {
            put("param1", "value1");
            put("param2", "value2");
            put("PARAM1", "value3");
        }
    };

    @Before
    public void initializeSystemProperties() {
        addToSystemProperties();
        configuration = new SystemPropertiesGameRepositoryConfiguration();
    }

    @Test
    public void testGetAllProperties() {
        List<String> propertiesNames = configuration.getParametersNames();
        Collections.sort(propertiesNames);

        List<String> initialPropertiesNames = Arrays.asList(properties.keySet().toArray(new String[0]));
        Collections.sort(initialPropertiesNames);

        Assert.assertThat("Initial parameters and current parameters are not equal.", propertiesNames, is(initialPropertiesNames));
    }

    @Test
    public void checkExistingProperty() {
        Assert.assertTrue(configuration.getParameter("param1").equals("value1"));
    }

    @Test
    public void checkNotExistingProperty() {
        Assert.assertTrue(configuration.getParameter("1") == null);
        Assert.assertTrue(configuration.getParameter("") == null);
        Assert.assertTrue(configuration.getParameter(null) == null);
    }

    private void addToSystemProperties() {
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            System.setProperty(SystemPropertiesGameRepositoryConfiguration.SYSTEM_PROPERTY_PREFIX_GAME_REPOSITORY_PROPERTIES
                + entry.getKey(), entry.getValue());
        }
    }

}
