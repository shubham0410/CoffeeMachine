package io.shubham.coffeemachine.VendingMachine;

import io.shubham.coffeemachine.Configurations.Configuration;
import io.shubham.coffeemachine.Drinks.BlackTea;
import io.shubham.coffeemachine.Drinks.GreenTea;
import io.shubham.coffeemachine.Drinks.HotCoffee;
import io.shubham.coffeemachine.Drinks.HotTea;
import io.shubham.coffeemachine.Ingredients.Ingredient;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.exit;

public class CoffeeVendingMachine {
    private static int outlets;
    private static HotTea hotTea;
    private static HotCoffee hotCoffee;
    private static GreenTea greenTea;
    private static BlackTea blackTea;
    private static Ingredient ingredient;

    public static void main(String[] args) {
        fetchConfigurationAndCreateObjects();

        Scanner input = new Scanner(System.in);
        int user_input;

        while (true) {
            System.out.println("How many drinks do you want? ");
            System.out.println("You can request for upto " + outlets + " drinks at a time\n");
            user_input = input.nextInt();
            if(user_input > outlets || user_input <= 0) {
                System.out.println("You have requested an invalid option\n Please retry\n");
                continue;
            }
            int[] requests = new int[user_input];
            System.out.println("Enter 1 for Hot_Tea\nEnter 2 for Hot_Coffee\n" +
                    "Enter 3 for Black Tea\nEnter 4 for Green Tea\n");
            System.out.println("To place order for multiple drinks, /" +
                    "please enter a space or line separated input\n");
            for(int i = 0; i < user_input ; i++) {
                requests[i] = input.nextInt();
            }

            processRequests(user_input, requests);

            //checking for refill
        }
    }

    private static void processRequests(int user_input, int[] requests) {
        //Threads
        ExecutorService executor = Executors.newFixedThreadPool(user_input);

        for(int i=0; i<user_input; i++){
            Runnable obj = new MyThread(""+ i, requests[i], ingredient,
                    hotTea, hotCoffee, blackTea, greenTea);
            executor.execute(obj);
        }
        executor.shutdown();
        //while all threads are not terminated or joined, wait
        while (!executor.isTerminated()) {
        }
        System.out.println("All the current requests have been processed!!\n");
        ingredient.checkLowIndicator();
        System.out.println("Enter 9 to exit\n 1 to continue");

        Scanner input = new Scanner(System.in);
        int input_exit = input.nextInt();
        if (input_exit == 9) {
                System.out.println("Goodbye!!\n");
                exit(0);
        }
    }


    private static void fetchConfigurationAndCreateObjects() {
        //FETCH INITIAL CONFIGURATION USING JSON CONFIG FILES
        String initConfigFileName = "configuration/coffee-machine.json";
        String lowIngredientsConfigFileName = "configuration/low-ingredients-indicator.json";
        final Configuration configuration = new Configuration(
                initConfigFileName, lowIngredientsConfigFileName);
        hotTea = new HotTea(configuration);
        hotCoffee = new HotCoffee(configuration);
        blackTea = new BlackTea(configuration);
        greenTea = new GreenTea(configuration);
        outlets = configuration.getNumberOfOutlets();
        ingredient = new Ingredient(configuration);
    }
}