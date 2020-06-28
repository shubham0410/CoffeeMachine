package io.shubham.coffeemachine.Configurations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ConfigurationTest {


    private Configuration configuration;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void FetchConfigurationShouldSetConfigWhenConfigFilesArePresent() {

        configuration = new Configuration("configuration/coffee-machine.json",
                "configuration/low-ingredients-indicator.json");
        Assert.assertNotNull(configuration.getBlackTeaIngredients());
        Assert.assertNotNull(configuration.getLowIngredientsIndicator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FetchConfigurationThrowExceptionWhenConfigFilesAreNotPresent() {

        configuration = new Configuration("configuration/invalid-coffee-machine.json",
                "configuration/low-ingredients-indicator.json");
        Assert.assertNull(configuration.getBlackTeaIngredients());
        Assert.assertNull(configuration.getLowIngredientsIndicator());
    }

    @Test(expected = NullPointerException.class)
    public void FetchConfigurationThrowExceptionWhenJsonIsNotInExpectedFormat() {

        configuration = new Configuration("configuration/invalid-file.json",
                "configuration/low-ingredients-indicator.json");
        Assert.assertNull(configuration.getBlackTeaIngredients());
    }
}