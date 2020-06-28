package io.shubham.coffeemachine.Configurations;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.Map;

public final class Configuration {

    private int numberOfOutlets;
    private Map<String, Long> ingredientsQuantity;
    private Map<String, Long> hotTeaIngredients;
    private Map<String, Long> hotCoffeeIngredients;
    private Map<String, Long> greenTeaIngredients;
    private Map<String, Long> blackTeaIngredients;
    private Map<String, Long> lowIngredientsIndicator;


    public Configuration(String init_config_file_name, String low_ingredients_config_file_name) {
        //READING INITIAL CONFIGURATION JSON
        findInitialConfigFileAndParse(init_config_file_name);

        //READING LOW INGREDIENTS INDICATOR FILE
        findMinimumRequiredIngredientsFileAndParse(low_ingredients_config_file_name);
    }

    private void findMinimumRequiredIngredientsFileAndParse(String low_ingredients_config_file_name) {
        JSONParser jsonParser = new JSONParser();
        URL resource = getClass().getClassLoader().getResource(low_ingredients_config_file_name);
        if(resource == null)
            throw new IllegalArgumentException("File not found");
        FileReader low_ingredients_config_file;
        try {
            low_ingredients_config_file = new FileReader(new File(resource.getFile()));
            Object ind = jsonParser.parse(low_ingredients_config_file);
            lowIngredientsIndicator = (JSONObject) ind;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void findInitialConfigFileAndParse(String init_config_file_name) {
        JSONParser jsonParser = new JSONParser();
        URL resource = getClass().getClassLoader().getResource(init_config_file_name);
        if(resource == null)
            throw new IllegalArgumentException("File not found");
        FileReader init_config_file;
        try {
            init_config_file = new FileReader(new File(resource.getFile()));
            Object obj = jsonParser.parse(init_config_file);

            //Parsing initial configuration file
            parseMachineObject(obj);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private void parseMachineObject(Object obj) {
        final JSONObject coffeeMachine = (JSONObject) obj;
        final JSONObject machine = (JSONObject) coffeeMachine.get("machine");
        JSONObject outlets = (JSONObject) machine.get("outlets");
        numberOfOutlets = ((Long) outlets.get("count_n")).intValue();
        ingredientsQuantity = (Map<String, Long>) machine.get("total_items_quantity");
        JSONObject beverages = (JSONObject) machine.get("beverages");
        hotTeaIngredients = (Map<String, Long>) beverages.get("hot_tea");
        hotCoffeeIngredients = (Map<String, Long>) beverages.get("hot_coffee");
        blackTeaIngredients = (Map<String, Long>) beverages.get("black_tea");
        greenTeaIngredients = (Map<String, Long>) beverages.get("green_tea");

    }

    //GETTERS AND SETTERS
    public int getNumberOfOutlets() {
        return numberOfOutlets;
    }

    public void setNumberOfOutlets(int numberOfOutlets) {
        this.numberOfOutlets = numberOfOutlets;
    }

    public Map<String, Long> getIngredientsQuantity() {
        return ingredientsQuantity;
    }

    public void setIngredientsQuantity(Map<String, Long> ingredientsQuantity) {
        this.ingredientsQuantity = ingredientsQuantity;
    }

    public Map<String, Long> getHotTeaIngredients() {
        return hotTeaIngredients;
    }

    public void setHotTeaIngredients(Map<String, Long> hotTeaIngredients) {
        this.hotTeaIngredients = hotTeaIngredients;
    }

    public Map<String, Long> getHotCoffeeIngredients() {
        return hotCoffeeIngredients;
    }

    public void setHotCoffeeIngredients(Map<String, Long> hotCoffeeIngredients) {
        this.hotCoffeeIngredients = hotCoffeeIngredients;
    }

    public Map<String, Long> getGreenTeaIngredients() {
        return greenTeaIngredients;
    }

    public void setGreenTeaIngredients(Map<String, Long> greenTeaIngredients) {
        this.greenTeaIngredients = greenTeaIngredients;
    }

    public Map<String, Long> getBlackTeaIngredients() {
        return blackTeaIngredients;
    }

    public void setBlackTeaIngredients(Map<String, Long> blackTeaIngredients) {
        this.blackTeaIngredients = blackTeaIngredients;
    }

    public Map<String, Long> getLowIngredientsIndicator() {
        return lowIngredientsIndicator;
    }

    public void setLowIngredientsIndicator(Map<String, Long> lowIngredientsIndicator) {
        this.lowIngredientsIndicator = lowIngredientsIndicator;
    }
}
