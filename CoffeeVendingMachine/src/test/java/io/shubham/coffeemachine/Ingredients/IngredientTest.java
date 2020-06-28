package io.shubham.coffeemachine.Ingredients;

import io.shubham.coffeemachine.Configurations.Configuration;
import io.shubham.coffeemachine.Drinks.BlackTea;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class IngredientTest {

    private Ingredient ingredient;
    private BlackTea blackTea;

    @Before
    public void setUp() throws Exception {
        String initConfigFileName = "configuration/coffee-machine.json";
        String lowIngredientsConfigFileName = "configuration/low-ingredients-indicator.json";
        Configuration configuration = new Configuration(initConfigFileName, lowIngredientsConfigFileName);

        ingredient = new Ingredient(configuration);

        blackTea = new BlackTea(configuration);
    }

    @Test
    public void shouldIndicateThatTheIngredientsAreLow() {
        String data = "0";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ingredient.setTeaLeavesSyrupIngredient(5);
        ingredient.checkLowIndicator();
        Assert.assertTrue(ingredient.isLowIngredients());
    }

    @Test
    public void shouldReturnTrueWhenTheDrinkRequestCanBeFulfilled() {
        Assert.assertTrue(ingredient.isValidRequest(blackTea));
    }

    @Test
    public void shouldReturnTrueWhenTheDrinkRequestCannotBeFulfilled() {
        ingredient.setHotWaterIngredient(200);
        Assert.assertFalse(ingredient.isValidRequest(blackTea));
    }
}