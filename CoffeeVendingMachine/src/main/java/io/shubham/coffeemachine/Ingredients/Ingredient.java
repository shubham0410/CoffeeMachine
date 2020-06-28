package io.shubham.coffeemachine.Ingredients;

import io.shubham.coffeemachine.Configurations.Configuration;
import io.shubham.coffeemachine.Drinks.Drink;

import java.util.Map;
import java.util.Scanner;

public class Ingredient {
    private int hotWaterIngredient;
    private int minHotWaterIngredient;
    private int hotMilkIngredient;
    private int minHotMilkIngredient;
    private int gingerSyrupIngredient;
    private int minGingerSyrupIngredient;
    private int sugarSyrupIngredient;
    private int minSugarSyrupIngredient;
    private int teaLeavesSyrupIngredient;
    private int minTeaLeavesSyrupIngredient;
    private boolean lowIngredients;

    public Ingredient(Configuration configuration) {
        Map<String, Long> hotCoffeeIngredients = configuration.getIngredientsQuantity();

        //SETTING AVAILABLE INGREDIENTS QUANTITY FOR ALL THE INGREDIENTS
        for (Map.Entry<String, Long> entry : hotCoffeeIngredients.entrySet()) {
            if(entry.getKey().equals("hot_water")) {
                setHotWaterIngredient(Math.toIntExact(entry.getValue()));
            }
            else if(entry.getKey().equals("hot_milk")) {
                setHotMilkIngredient(Math.toIntExact(entry.getValue()));
            }
            else if(entry.getKey().equals("ginger_syrup")) {
                setGingerSyrupIngredient(Math.toIntExact(entry.getValue()));
            }
            else if(entry.getKey().equals("sugar_syrup")) {
                setSugarSyrupIngredient(Math.toIntExact(entry.getValue()));
            }
            else if(entry.getKey().equals("tea_leaves_syrup")) {
                setTeaLeavesSyrupIngredient(Math.toIntExact(entry.getValue()));
            }
        }

        //SETTING INGREDIENTS FOR MINIMUM QUANTITY REQUIRED FROM CONFIGURATION FILE
        Map<String, Long> minIngredients = configuration.getLowIngredientsIndicator();
        for (Map.Entry<String, Long> entry : minIngredients.entrySet()) {
            switch (entry.getKey()) {
                case "hot_water":
                    setMinHotWaterIngredient(Math.toIntExact(entry.getValue()));
                    break;
                case "hot_milk":
                    setMinHotMilkIngredient(Math.toIntExact(entry.getValue()));
                    break;
                case "ginger_syrup":
                    setMinGingerSyrupIngredient(Math.toIntExact(entry.getValue()));
                    break;
                case "sugar_syrup":
                    setMinSugarSyrupIngredient(Math.toIntExact(entry.getValue()));
                    break;
                case "tea_leaves_syrup":
                    setMinTeaLeavesSyrupIngredient(Math.toIntExact(entry.getValue()));
                    break;
            }
        }
        setLowIngredients(false);
    }

    //Checking if enough ingredients are present for the type of drink requested
    public boolean isValidRequest(Drink drink) {
        String drinkName = drink.getClass().getSimpleName();
        if(this.gingerSyrupIngredient < drink.getGingerSyrupQuant()) {
            System.out.println(drinkName + " cannot be prepared because ginger_syrup" +
                    " is not available\n");
            return false;
        }
        else if(this.hotMilkIngredient < drink.getHotMilkQuant()) {
            System.out.println(drinkName + " cannot be prepared because hot_milk" +
                    " is not available\n");
            return false;
        }
        else if(this.hotWaterIngredient < drink.getHotWaterQuant()) {
            System.out.println(drinkName + " cannot be prepared because hot_water" +
                    " is not sufficient\n");
            return false;
        }
        else if(this.sugarSyrupIngredient < drink.getSugarSyrupQuant()) {
            System.out.println(drinkName + " cannot be prepared because sugar_syrup" +
                    " is not available\n");
            return false;
        }
        else if(this.teaLeavesSyrupIngredient < drink.getTeaLeavesSyrupQuant()) {
            System.out.println(drinkName + " cannot be prepared because hot_water" +
                    " is not sufficient\n");
            return false; //return false for a request which cannot be fulfilled
        }

        //decrease the Ingredients quantity with the current drink
        this.gingerSyrupIngredient -= drink.getGingerSyrupQuant();
        this.hotMilkIngredient -= drink.getHotMilkQuant();
        this.hotWaterIngredient -= drink.getHotWaterQuant();
        this.teaLeavesSyrupIngredient -= drink.getTeaLeavesSyrupQuant();
        this.sugarSyrupIngredient -= drink.getSugarSyrupQuant();

        return true;
    }

    //CHECK IF ANY OF THE INGREDIENT QUANTITY IS LOWER THAN THE MINIMUM REQUIRED QUANTITY
    public void checkLowIndicator() {
        if(getMinHotWaterIngredient() > getHotWaterIngredient() ||
        getMinHotMilkIngredient() > getHotMilkIngredient() ||
        getMinGingerSyrupIngredient() > getGingerSyrupIngredient() ||
        getMinSugarSyrupIngredient() > getSugarSyrupIngredient() ||
        getMinTeaLeavesSyrupIngredient() > getTeaLeavesSyrupIngredient()) {

            setLowIngredients(true);        // Setting the boolean lowIngredients to be TRUE
            System.out.println("Ingredients are low, do you want to refill now, press 1/0?\n");
            Scanner input = new Scanner(System.in);
            int refill = input.nextInt();
            if(refill == 1) {
                refillIngredients();
            }
        }
        else {
            System.out.println("Ingredients are not low\n");
        }
    }

    private void refillIngredients() {
        System.out.println("Displaying the present ingredients quantity\n");
        System.out.println("Hot Water = "+ getHotWaterIngredient()+
                        "\nHot Milk = " + getHotMilkIngredient() +
                "\nGinger Syrup = " + getGingerSyrupIngredient() +
                "\nSugar Syrup = " + getSugarSyrupIngredient() +
                "\nTea Leaves Syrup = " + getTeaLeavesSyrupIngredient());
        System.out.println("Please enter the quantity of ingredients you " +
                "want to refill in the same order (0 for no refill)\n");
        int refill;
        Scanner input = new Scanner(System.in);
        for(int i=0; i<5 ; i++) {
            refill = input.nextInt();
            if(i==0) {
                setHotWaterIngredient(getHotWaterIngredient()+refill);
            }
            else if(i==1){
                setHotMilkIngredient(getHotMilkIngredient()+refill);
            }
            else if(i==2){
                setGingerSyrupIngredient(getGingerSyrupIngredient()+refill);
            }
            else if(i==3){
                setSugarSyrupIngredient(getSugarSyrupIngredient()+refill);
            }
            else{
                setTeaLeavesSyrupIngredient(getTeaLeavesSyrupIngredient()+refill);
            }
        }
        checkLowIndicator();
    }

    //GETTERS AND SETTERS
    int getHotWaterIngredient() {
        return hotWaterIngredient;
    }

    void setHotWaterIngredient(int hotWaterIngredient) {
        this.hotWaterIngredient = hotWaterIngredient;
    }

    int getMinHotWaterIngredient() {
        return minHotWaterIngredient;
    }

    void setMinHotWaterIngredient(int minHotWaterIngredient) {
        this.minHotWaterIngredient = minHotWaterIngredient;
    }

    int getHotMilkIngredient() {
        return hotMilkIngredient;
    }

    void setHotMilkIngredient(int hotMilkIngredient) {
        this.hotMilkIngredient = hotMilkIngredient;
    }

    int getMinHotMilkIngredient() {
        return minHotMilkIngredient;
    }

    void setMinHotMilkIngredient(int minHotMilkIngredient) {
        this.minHotMilkIngredient = minHotMilkIngredient;
    }

    int getGingerSyrupIngredient() {
        return gingerSyrupIngredient;
    }

    void setGingerSyrupIngredient(int gingerSyrupIngredient) {
        this.gingerSyrupIngredient = gingerSyrupIngredient;
    }

    int getMinGingerSyrupIngredient() {
        return minGingerSyrupIngredient;
    }

    void setMinGingerSyrupIngredient(int minGingerSyrupIngredient) {
        this.minGingerSyrupIngredient = minGingerSyrupIngredient;
    }

    int getSugarSyrupIngredient() {
        return sugarSyrupIngredient;
    }

    void setSugarSyrupIngredient(int sugarSyrupIngredient) {
        this.sugarSyrupIngredient = sugarSyrupIngredient;
    }

    int getMinSugarSyrupIngredient() {
        return minSugarSyrupIngredient;
    }

    void setMinSugarSyrupIngredient(int minSugarSyrupIngredient) {
        this.minSugarSyrupIngredient = minSugarSyrupIngredient;
    }

    int getTeaLeavesSyrupIngredient() {
        return teaLeavesSyrupIngredient;
    }

    void setTeaLeavesSyrupIngredient(int teaLeavesSyrupIngredient) {
        this.teaLeavesSyrupIngredient = teaLeavesSyrupIngredient;
    }

    int getMinTeaLeavesSyrupIngredient() {
        return minTeaLeavesSyrupIngredient;
    }

    void setMinTeaLeavesSyrupIngredient(int minTeaLeavesSyrupIngredient) {
        this.minTeaLeavesSyrupIngredient = minTeaLeavesSyrupIngredient;
    }

    boolean isLowIngredients() {
        return lowIngredients;
    }

    void setLowIngredients(boolean lowIngredients) {
        this.lowIngredients = lowIngredients;
    }
}
