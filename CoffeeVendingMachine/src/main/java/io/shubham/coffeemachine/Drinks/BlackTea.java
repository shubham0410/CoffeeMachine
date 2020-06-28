package io.shubham.coffeemachine.Drinks;

import io.shubham.coffeemachine.Configurations.Configuration;

import java.util.Map;

public class BlackTea extends Drink {

    public BlackTea(Configuration configuration) {
        Map<String, Long> blackTeaIngredients = configuration.getBlackTeaIngredients();
        for (Map.Entry<String, Long> entry : blackTeaIngredients.entrySet()) {
            switch (entry.getKey()) {
                case "hot_water":
                    setHotWaterQuant(Math.toIntExact(entry.getValue()));
                    break;
                case "hot_milk":
                    setHotMilkQuant(Math.toIntExact(entry.getValue()));
                    break;
                case "ginger_syrup":
                    setGingerSyrupQuant(Math.toIntExact(entry.getValue()));
                    break;
                case "sugar_syrup":
                    setSugarSyrupQuant(Math.toIntExact(entry.getValue()));
                    break;
                case "tea_leaves_syrup":
                    setTeaLeavesSyrupQuant(Math.toIntExact(entry.getValue()));
                    break;
            }
        }
    }
}
