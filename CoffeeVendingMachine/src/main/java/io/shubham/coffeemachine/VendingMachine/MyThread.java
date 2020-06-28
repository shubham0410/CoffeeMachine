package io.shubham.coffeemachine.VendingMachine;

import io.shubham.coffeemachine.Drinks.BlackTea;
import io.shubham.coffeemachine.Drinks.GreenTea;
import io.shubham.coffeemachine.Drinks.HotCoffee;
import io.shubham.coffeemachine.Drinks.HotTea;
import io.shubham.coffeemachine.Ingredients.Ingredient;

import static java.lang.System.exit;

public class MyThread implements Runnable {
    private String name;
    private int request;
    private Ingredient ingredient;
    private HotTea hotTea;
    private HotCoffee hotCoffee;
    private BlackTea blackTea;
    private GreenTea greenTea;

    //Constructor for MyThread
    MyThread(String name, int request, Ingredient ingredient, HotTea hotTea,
             HotCoffee hotCoffee, BlackTea blackTea, GreenTea greenTea) {
        this.name = name;
        this.request = request;
        this.ingredient = ingredient;
        this.hotCoffee = hotCoffee;
        this.hotTea = hotTea;
        this.blackTea = blackTea;
        this.greenTea = greenTea;
    }

    //logic to be performed inside each thread
    public void run() {
        switch (request) {
            case 1:
                if (ingredient.isValidRequest(hotTea)) {
                    System.out.println("hot_tea is prepared\n");
                }
                break;

            case 2:
                if (ingredient.isValidRequest(hotCoffee)) {
                    System.out.println("hot_coffee is prepared\n");
                }
                break;

            case 3:
                if (ingredient.isValidRequest(blackTea)) {
                    System.out.println("black_tea is prepared\n");
                }
                break;

            case 4:
                //WE CAN ADD A SLEEP TIMER FOR THREAD TO TEST THE WORKING
//                try {
//                    Thread.sleep(10L * 1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                if (ingredient.isValidRequest(greenTea)) {
                    System.out.println("green_tea is prepared\n");
                }
                break;

            default:
                System.out.println("Sorry You selected an invalid Input\nPlease enter a valid option\n");
        }
    }
}
