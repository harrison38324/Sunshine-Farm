package ui;

import java.util.Scanner;

import model.*;

// Sunshine Farm application
public class RunProgram {
    private Scanner input;

    private Plants norPlant;
    private Plants rarePlant;
    private Plants legendPlant;
    private Fertilizer norFertilizer;
    private Fertilizer rareFertilizer;
    private Fertilizer legendFertilizer;

    private PlantsStorage plantsCommodity;
    private FertilizerStorage fertilizerCommodity;
    private PlantsStorage plantsStorage;
    private FertilizerStorage fertilizerStorage;

    private Wallet wallet;

    private PlantsSlots plantsSlot;

    // EFFECTS: run run the Sunshine Farm application
    public RunProgram() {
        runProgram();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runProgram() {
        boolean keepGoing = true;
        String command = null;

        init();
        farmDefaultValue();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                chooseSubMenu(command);
            }
        }
        System.out.println("\nGood bye!");
    }

    // MODIFIES: this
    // EFFECTS: initiate the game
    private void init() {
        norPlant = new Plants("Apple", 50, 50);
        rarePlant = new Plants("blueberry", 100, 80);
        legendPlant = new Plants("Golden Apple", 200, 100);

        norFertilizer = new Fertilizer("Normal Fertilizer", 10, 20);
        rareFertilizer = new Fertilizer("Rare Fertilizer", 30, 70);
        legendFertilizer = new Fertilizer("legend Fertilizer", 60, 200);

        plantsCommodity = new PlantsStorage();
        fertilizerCommodity = new FertilizerStorage();

        plantsStorage = new PlantsStorage();
        fertilizerStorage = new FertilizerStorage();

        wallet = new Wallet();

        plantsSlot = new PlantsSlots();

        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // MODIFIES: this
    // EFFECTS: set default value to the Farm status
    private void farmDefaultValue() {
        plantsCommodity.addPlants(norPlant);
        plantsCommodity.addPlants(rarePlant);
        plantsCommodity.addPlants(legendPlant);

        fertilizerCommodity.addFertilizer(norFertilizer);
        fertilizerCommodity.addFertilizer(rareFertilizer);
        fertilizerCommodity.addFertilizer(legendFertilizer);

        plantsStorage.addPlants(norPlant);
        fertilizerStorage.addFertilizer(norFertilizer);
        fertilizerStorage.addFertilizer(norFertilizer);

        wallet.earn(1000);
    }

    // EFFECTS: display the main Menu of options for user
    private void displayMainMenu() {
        System.out.println("\nHello,Sunshine Farm, what do you want to do today?");
        System.out.println("\nf -> go to farm");
        System.out.println("\nm -> go to market");
        System.out.println("\nq -> quit game");
    }

    // MODIFIES: this
    // EFFECTS: choose sub Menu based on the user's input command
    private void chooseSubMenu(String command) {
        if (command.equals("f")) {
            farmMenu();
        } else if (command.equals("m")) {
            marketMenu();
        } else {
            System.out.println("Input is Not a valid value...");
        }
    }

    // MODIFIES: this
    // EFFECTS: display market Menu and buy items based on user's input command
    private void marketMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            printMarketCommodity();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("b")) {
                keepGoing = false;
            } else {
                tryBuyingIndicatedCommodity(command);
            }
        }
    }

    // EFFECTS: display market Commodity and basic operation instruction
    private void printMarketCommodity() {
        System.out.println("\nThese items are on sell:");
        System.out.println("\nPlants:");
        printPlantsStorageStatusList(plantsCommodity);
        System.out.println("\nFertilizer:");
        printFertilizerStorageStatusList(fertilizerCommodity);
        System.out.println("Your balance: " + wallet.getBalance());
        System.out.println("\nWhat do you want to buy, p -> Plants, f -> Fertilizer, b -> back");
    }

    // MODIFIES: this
    // EFFECTS: try to buy Indicated commodity
    private void tryBuyingIndicatedCommodity(String command) {
        int intCommand = 0;
        if (command.equals("p")) {
            printPlantsStorageStatusList(plantsCommodity);
            System.out.println("Please type the order num of plants you want to buy");
            System.out.println("Type 0 to go back to the upper menu");
            intCommand = validInputInt();
            tryBuyingIndicatedPlants(intCommand);
        } else if (command.equals("f")) {
            printFertilizerStorageStatusList(fertilizerCommodity);
            System.out.println("Please type the order num of fertilizer you want to buy");
            System.out.println("Type 0 to go back to the upper menu");
            intCommand = validInputInt();
            tryBuyingIndicatedFertilizer(intCommand);
        } else {
            System.out.println("\n Invalid Input");
        }
    }

    // MODIFIES: this
    // EFFECTS: valid the user's input value to be int
    private int validInputInt(){
        while (!input.hasNextInt()) {
            System.out.println("That's not a number!");
            input.next();
        }
        return input.nextInt();
    }
    // MODIFIES: this
    // EFFECTS: try to buy Indicated Plants
    private void tryBuyingIndicatedPlants(int command) {
        if (command > plantsCommodity.getPlantsStorageSize()) {
            System.out.println("Input number is too large");
        } else if (command < 0) {
            System.out.println("Input number cannot be negative");
        } else {
            Plants indicatedPlants = plantsCommodity.getPlantsStorage().get(command - 1);
            int price = indicatedPlants.getPrice();
            int balance = wallet.getBalance();
            if (balance >= price) {
                plantsStorage.addPlants(indicatedPlants);
                wallet.spend(price);
                System.out.println("You have bought that Plants");
            } else {
                System.out.println("You don't have sufficient Balance");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: try to buy Indicated Fertilizer
    private void tryBuyingIndicatedFertilizer(int command) {
        if (command > fertilizerCommodity.getFertilizerStorage().size()) {
            System.out.println("Input number is too large");
        } else if (command < 0) {
            System.out.println("Input number cannot be negative");
        } else {
            Fertilizer indicatedFertilizer = fertilizerCommodity.getFertilizerStorage().get(command - 1);
            int price = indicatedFertilizer.getPrice();
            int balance = wallet.getBalance();
            if (balance >= price) {
                fertilizerStorage.addFertilizer(indicatedFertilizer);
                wallet.spend(price);
                System.out.println("You have bought that Fertilizer");
            } else {
                System.out.println("You don't have sufficient Balance");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: show the Farm Menu and process user's input
    private void farmMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayFarmMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                operationInFarm(command);
            }
        }
    }

    // EFFECTS: display the Farm Menu
    private void displayFarmMenu() {
        System.out.println("\nWelcome to your Farm, what do you want to do");
        System.out.println("\tcf -> Check the fertilizer I owned");
        System.out.println("\tcp -> Check the Plants I owned");
        System.out.println("\tcs -> Check Slots Status that planted");
        System.out.println("\tpp -> Plant the Plants I owned");
        System.out.println("\tuf -> Use fertilizer on the planted plants");
        System.out.println("\tsp -> sell mature Plants in the farm slots");
        System.out.println("\tb  -> Back to the upper Menu");
    }

    // MODIFIES: this
    // EFFECTS: excute the Operation based on the Input command
    private void operationInFarm(String command) {
        if (command.equals("cf")) {
            printFertilizerStorageNameList(fertilizerStorage);
        } else if (command.equals("cp")) {
            printPlantsStorageNameList(plantsStorage);
        } else if (command.equals("pp")) {
            plantPlants();
        } else if (command.equals("uf")) {
            useFertilizer();
        } else if (command.equals("cs")) {
            printPlantsSlotStatusList();
        } else if (command.equals("sp")) {
            sellMaturePlants();
        } else {
            System.out.println("Input is Not a valid Value...");
        }
    }

    // MODIFIES: this
    // EFFECTS: Sell Plants that is Mature
    private void sellMaturePlants() {
        boolean keepGoing = true;
        int command = 0;
        while (keepGoing) {
            printPlantsSlotStatusList();
            System.out.println("Type the order number (nth) Mature plant you want to sell");
            System.out.println("Type 0 to go back to the upper menu");
            command = validInputInt();
            if (command == 0) {
                keepGoing = false;
            } else {
                trySellPlants(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: try to sell the Plants
    private void trySellPlants(int command) {
        if (command > plantsSlot.getPlantsStorageSize()) {
            System.out.println("\nInput number is larger than you have");
        } else if (command < 0) {
            System.out.println("\nInput number cannot be negative");
        } else {
            Plants indicatedPlants = plantsSlot.getPlants(command - 1);
            int growthTime = indicatedPlants.getGrowthTime();
            int price = indicatedPlants.getPrice();
            if (growthTime == 0) {
                wallet.earn(price * 2);
                System.out.println("\nYou sold " + indicatedPlants.getPlantsName() + " and get " + price * 2);
                plantsSlot.remove(command - 1);
            } else {
                System.out
                        .println("\nThe Plant " + indicatedPlants.getPlantsName() + " is not mature,(growth time > 0)");
            }
        }
    }

    // EFFECT: print the fertilizerStorage Name list
    private void printPlantsSlotStatusList() {
        System.out.println("\n The Slot status:");
        System.out.println("\n-----------------");
        for (int i = 1; i <= plantsSlot.getPlantsStorageSize(); i++) {
            String name = "Plant Name:" + plantsSlot.getPlants(i - 1).getPlantsName() + " Time to grow up: "
                    + plantsSlot.getPlants(i - 1).getGrowthTime();
            System.out.println(name);
        }
        System.out.println("\n-----------------");
    }

    // EFFECTS: print the FertilizerStorage Name list
    private void printFertilizerStorageNameList(FertilizerStorage fertilizerStorage) {
        System.out.println("\n The Fertilizer you have:");
        System.out.println("\n-----------------");
        for (int i = 1; i <= fertilizerStorage.getFertilizerStorage().size(); i++) {
            String name = fertilizerStorage.getNameofFertilizeri(i - 1);
            System.out.println(name);
        }
        System.out.println("\n-----------------");
    }

    // EFFECTS: print the PlantsStorage Name list
    private void printPlantsStorageNameList(PlantsStorage plantsStorage) {
        System.out.println("\n The Fertilizer you have:");
        System.out.println("\n-----------------");
        for (int i = 1; i <= plantsStorage.getPlantsStorageSize(); i++) {
            String name = plantsStorage.getNameofPlantsi(i - 1);
            System.out.println(name);
        }
        System.out.println("\n-----------------");
    }

    // EFFECTS: print the PlantsStorage Status list
    private void printPlantsStorageStatusList(PlantsStorage plantsStorage) {
        for (int i = 1; i <= plantsStorage.getPlantsStorageSize(); i++) {
            String name = plantsStorage.getNameofPlantsi(i - 1);
            int price = plantsStorage.getiPlants(i - 1).getPrice();
            int growthTime = plantsStorage.getiPlants(i - 1).getGrowthTime();
            System.out.println(name + " its price: " + price + " its growthTime: " + growthTime);
        }
    }

    // EFFECTS: print the FertilizerStorage Status list
    private void printFertilizerStorageStatusList(FertilizerStorage fertilizerStorage) {
        for (int i = 1; i <= fertilizerStorage.getFertilizerStorage().size(); i++) {
            String name = fertilizerStorage.getNameofFertilizeri(i - 1);
            int price = fertilizerStorage.getiFertilizer(i - 1).getPrice();
            int timeReduced = fertilizerStorage.getiFertilizer(i - 1).getTimeReduced();
            System.out.println(name + " its price: " + price + " Time reduce for plant to grow: " + timeReduced);
        }
    }

    // MODIFIES: this
    // EFFECTS: show the plant options and process user's input
    private void plantPlants() {
        boolean keepGoing = true;
        int command = 0;
        while (keepGoing) {

            printIntoInfoPlantPlants();
            command = validInputInt();

            if (command == 0) {
                keepGoing = false;
            } else {
                tryPlantPlants(command);
            }
        }
    }

    // EFFECTS: print the introduction information for user to plant plants
    private void printIntoInfoPlantPlants() {
        printPlantsStorageNameList(plantsStorage);
        System.out.println("\nPlease type the order number of the Plants you want to plant");
        System.out.println("\n type 0 to go back to the upper menu");
    }

    // MODIFIES: this
    // EFFECTS: try to plant Plants from the PlantsStorage
    private void tryPlantPlants(int command) {
        if (command > plantsStorage.getPlantsStorageSize()) {
            System.out.println("Input number is larger than you have");
        } else if (command < 0) {
            System.out.println("Input number cannot be negative");
        } else {
            plantsSlot.addPlants(plantsStorage.getPlantsStorage().get(command - 1));
            plantsStorage.remove(command - 1);
            System.out.println("\nPlant successfully!");
        }
    }

    // MODIFIES: this
    // EFFECTS: show the fertilizer-using options and process user's input
    private void useFertilizer() {
        boolean keepGoing = true;
        int fertilizerCommand = 0;
        int slotChosenCommand = 0;
        while (keepGoing) {
            printFertilizerCmdInstruct();
            fertilizerCommand = validInputInt();
            if (fertilizerCommand == 0) {
                keepGoing = false;
            } else if (validFertilizerCmd(fertilizerCommand)) {
                printSlotChosenCmdInstruct();
                slotChosenCommand = validInputInt();
                if (slotChosenCommand == 0) {
                    keepGoing = false;
                } else {
                    tryApplyFertilizer(slotChosenCommand, fertilizerCommand);
                }
            } else {
                System.out.println("Invalid Input(negative number or larger than the num of fertilizers you have)");
            }
        }
    }

    // EFFECTS: print instruction for Fertilizer command
    private void printFertilizerCmdInstruct() {
        printFertilizerStorageNameList(fertilizerStorage);
        System.out.println("\nPlease type the order number of the Fertilizer you want to use");
        System.out.println("\n type 0 to go back to the upper menu");
    }

    // EFFECTS: verify indicated fertilizer exist
    private boolean validFertilizerCmd(int fertilizerCommand) {
        return ((fertilizerCommand > 0) && (fertilizerCommand <= fertilizerStorage.getFertilizerStorage().size()));
    }

    // EFFECTS: print instruction for slot chosen command
    private void printSlotChosenCmdInstruct() {
        System.out.println("\nPlease type the order number of the plant  you want to apply that fertilizer");
        printPlantsSlotStatusList();
        System.out.println("\n type 0 to go back to the upper menu");
    }

    // REQUIRES: fertilizerCommand > 0 and fertilizerCommand <= the size of
    // fertilizerStorage
    // MODIFIES: this
    // EFFECTS: try to apply the fertilizer to the plant that user indicated
    private void tryApplyFertilizer(int slotChosenCommand, int fertilizerCommand) {
        if (slotChosenCommand > plantsSlot.getPlantsStorageSize()) {
            System.out.println("Input number is larger than you have");
        } else if (slotChosenCommand < 0) {
            System.out.println("Input number cannot be negative");
        } else {
            int timeReduced = fertilizerStorage.getFertilizerStorage().get(fertilizerCommand - 1).getTimeReduced();
            Plants incdicatedPlants = plantsSlot.getPlants(slotChosenCommand - 1);
            if (timeReduced > incdicatedPlants.getGrowthTime()) {
                incdicatedPlants.setGrowthTime(0);
                fertilizerStorage.remove(fertilizerCommand - 1);
            } else {
                incdicatedPlants.decreaseGrowthTime(timeReduced);
                fertilizerStorage.remove(fertilizerCommand - 1);
            }
            System.out.println("\nApply fertilizer Succussfully!");
            printPlantsSlotStatusList();
        }
    }
}