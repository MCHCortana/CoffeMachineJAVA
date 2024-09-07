package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static boolean checkIngredients(int[]suppliesOneCoffee, int[] supplies) {
                
        int[] maxNumCoffeesAllIngredinece = new int[5];
        int indexMissingingredient = 10;
        boolean haveEnough = true;
            
        for (int i = 0; i < supplies.length - 1; i++ ) {
            if (supplies[i] < suppliesOneCoffee[i]){
                indexMissingingredient = i;
            }
        }

        if (indexMissingingredient == 0){
             haveEnough = false;
            System.out.println("Sorry, not enough water!");
        } else if (indexMissingingredient == 1) {
             haveEnough = false;
           System.out.println("Sorry, not enough milk!"); 
        } else if (indexMissingingredient == 2) {
             haveEnough = false;
           System.out.println("Sorry, not enough coffe beans!");
        } else if (supplies[3] == 0) {
             haveEnough = false;
           System.out.println("Sorry, not enough cups!"); 
        }  else if (supplies[3] >=1) {
            haveEnough = true;
            System.out.println("I have enough resources, making you a coffee!");
        }
        return haveEnough;
        
    }

    public static void fillingUp ( Scanner scanner, int[] currentSupplies){
        System.out.println("Write how many ml of water you want to add: ");
        int waterSup = scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        int milkSup = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int beansSup = scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add: ");
        int cupsSup = scanner.nextInt();

        int[] adding = {waterSup, milkSup, beansSup, cupsSup};

        for (int i = 0; i < adding.length; i++){
            currentSupplies[i] = currentSupplies[i] + adding[i];
            System.out.println("Suplies" + currentSupplies[i] ); 
            System.out.println("adding" + adding[i] ); 
        }


        
    }

    public static void makeCoffe(int []currentSupplies, String coffee){

        int[] esspresso = {250, 0, 16, 1, 4};
        int[] latte = {350, 75, 20, 1, 7};
        int[] capuccino = {200, 100, 12, 1, 6};
        int[] ingedients = new int[5];

        switch (coffee) {
            case "1":
                ingedients = esspresso;
                break;
            case "2":
                ingedients = latte;
                break;
            case "3":
                ingedients = capuccino;
                break; 
            case "break":
                break;
            default:
                System.out.println("no such coffee");
                break;
        }
        if (checkIngredients(ingedients, currentSupplies)){
            for (int i = 0; i < (currentSupplies.length - 1 ); i++ ){
                currentSupplies[i] = currentSupplies[i] - ingedients[i];
        }
        currentSupplies[4] = currentSupplies[4] + ingedients[4];
        }
       
    }
    
    public static void summary(int[] currentSupplies) {

        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water%n", currentSupplies[0]);
        System.out.printf("%d of milk%n", currentSupplies[1]);
        System.out.printf("%d g of coffee beans%n", currentSupplies[2]);
        System.out.printf("%d disposable cups%n", currentSupplies[3]);
        System.out.printf("$%d of money%n", currentSupplies[4]);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
      
        int[] currentSupplies = {400, 540, 120, 9, 550};

        boolean running = true;
        
        while(running) {
            System.out.println("Write action (buy, fill, take): ");
            String action = scanner.next();
            switch (action) {
                case "take":
                    System.out.printf("I gave you $%d%n", currentSupplies[4]);
                    currentSupplies[4] = 0;
                    break;
                case "fill": 
                    fillingUp(scanner, currentSupplies);
                    break;
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    String coffee = scanner.next();
                    makeCoffe(currentSupplies, coffee);
                    break;
                case "remaining":
                    summary(currentSupplies);
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }
        scanner.close();
    }
}
