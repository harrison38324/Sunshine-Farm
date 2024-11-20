package ui;

import java.io.IOException;
import java.util.Scanner;

import model.*;
import persistence.LoadGameData;
import persistence.SaveGameData;

// the ui for the whole application
// have functions for all the application
// have farm menu which you can plant Plants,use Fertillizer,check status of the farm, sell mature plants
// have market menu which you can buy agricultural entities
public class ConsoleUI {
    private CoreData coreData;
    private Scanner input;

    // private Plants norPlant;
    // private Plants rarePlant;
    // private Plants legendPlant;
    // private Fertilizer norFertilizer;
    // private Fertilizer rareFertilizer;
    // private Fertilizer legendFertilizer;

    // private PlantsStorage plantsCommodity;
    // private FertilizerStorage fertilizerCommodity;
    // private PlantsStorage plantsStorage;
    // protected FertilizerStorage fertilizerStorage;

    // private Wallet wallet;

    // private PlantsSlots plantsSlot;

    // private LoadGameData loadGameData;
    // private SaveGameData saveGameData;
    private static final String JSON_STORE = "./data/gamedata.json";

    // EFFECTS: run run the Sunshine Farm application
    public ConsoleUI() {
        runProgram();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runProgram() {
        boolean keepGoing = true;
        String command = null;

        coreData = new CoreData();

        init();

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
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: display the main Menu of options for user
    private void displayMainMenu() {
        System.out.println("\nHello,Sunshine Farm, what do you want to do today?");
        System.out.println("\nf -> go to farm");
        System.out.println("\nm -> go to market");
        System.out.println("\ns -> save game data");
        System.out.println("\nl -> load game data");
        System.out.println("\nq -> quit game");
    }

    // MODIFIES: this
    // EFFECTS: choose sub Menu based on the user's input command
    private void chooseSubMenu(String command) {
        if (command.equals("f")) {
            farmMenu();
        } else if (command.equals("m")) {
            marketMenu();
        } else if (command.equals("l")) {
            coreData.loadGameData = new LoadGameData(JSON_STORE);
            try {
                coreData.loadGameData.loadGameData(coreData.wallet, coreData.fertilizerStorage,
                        coreData.plantsStorage, coreData.plantsSlot);
                printLoadedData();
            } catch (Exception e) {
                System.out.println("Fail to load Game Data");
            }
        } else if (command.equals("s")) {
            coreData.saveGameData = new SaveGameData(JSON_STORE);
            try {
                coreData.saveGameData.saveGameData(coreData.wallet, coreData.fertilizerStorage,
                        coreData.plantsStorage, coreData.plantsSlot);
                System.out.println("Save successfully!");
            } catch (IOException e) {
                System.out.println("Fail to save Game Data");
            }
        } else {
            System.out.println("Input is Not a valid value...");
        }
    }

    // EFFECTS: print the loaded data after loaded the data
    private void printLoadedData() {
        System.out.println("Load successfully!");
        System.out.println("\nNow you have:");
        System.out.println("Your Balance: " + coreData.wallet.getBalance());
        printFertilizerStorageNameList(coreData.fertilizerStorage);
        printPlantsStorageNameList(coreData.plantsStorage);
        printPlantsSlotStatusList();
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
        printPlantsStorageStatusList(coreData.plantsCommodity);
        System.out.println("\nFertilizer:");
        printFertilizerStorageStatusList(coreData.fertilizerCommodity);
        System.out.println("Your balance: " + coreData.wallet.getBalance());
        System.out.println("\nWhat do you want to buy, p -> Plants, f -> Fertilizer, b -> back");
    }

    // MODIFIES: this
    // EFFECTS: try to buy Indicated commodity
    private void tryBuyingIndicatedCommodity(String command) {
        int intCommand = 0;
        if (command.equals("p")) {
            printPlantsStorageStatusList(coreData.plantsCommodity);
            System.out.println("Please type the order num of plants you want to buy");
            System.out.println("Type 0 to go back to the upper menu");
            intCommand = validInputInt();
            tryBuyingIndicatedPlants(intCommand);
        } else if (command.equals("f")) {
            printFertilizerStorageStatusList(coreData.fertilizerCommodity);
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
    private int validInputInt() {
        while (!input.hasNextInt()) {
            System.out.println("That's not a number!");
            input.next();
        }
        return input.nextInt();
    }

    // MODIFIES: this
    // EFFECTS: try to buy Indicated Plants
    private void tryBuyingIndicatedPlants(int command) {
        if (command > coreData.plantsCommodity.getStorageSize()) {
            System.out.println("Input number is too large");
        } else if (command < 0) {
            System.out.println("Input number cannot be negative");
        } else if (command > 0) {
            AgriculturalEntity indicatedPlants = coreData.plantsCommodity.geti(command - 1);
            int price = indicatedPlants.getPrice();
            int balance = coreData.wallet.getBalance();
            if (balance >= price) {
                coreData.plantsStorage.buyEntity(indicatedPlants);
                coreData.wallet.spend(price);
                System.out.println("You have bought that Plants");
            } else {
                System.out.println("You don't have sufficient Balance");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: try to buy Indicated Fertilizer
    private void tryBuyingIndicatedFertilizer(int command) {
        if (command > coreData.fertilizerCommodity.getStorage().size()) {
            System.out.println("Input number is too large");
        } else if (command < 0) {
            System.out.println("Input number cannot be negative");
        } else if (command > 0) {
            AgriculturalEntity indicatedFertilizer = coreData.fertilizerCommodity.geti(command - 1);
            int price = indicatedFertilizer.getPrice();
            int balance = coreData.wallet.getBalance();
            if (balance >= price) {
                coreData.fertilizerStorage.buyEntity(indicatedFertilizer);
                coreData.wallet.spend(price);
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
            printFertilizerStorageNameList(coreData.fertilizerStorage);
        } else if (command.equals("cp")) {
            printPlantsStorageNameList(coreData.plantsStorage);
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
        if (command > coreData.plantsSlot.getStorageSize()) {
            System.out.println("\nInput number is larger than you have");
        } else if (command < 0) {
            System.out.println("\nInput number cannot be negative");
        } else {
            AgriculturalEntity indicatedPlants = coreData.plantsSlot.getPlants(command - 1);
            int growthTime = indicatedPlants.getTime();
            int price = indicatedPlants.getPrice();
            if (growthTime == 0) {
                coreData.wallet.earn(price * 2);
                System.out.println("\nYou sold " + indicatedPlants.getName() + " and get " + price * 2);
                coreData.plantsSlot.remove(command - 1);
            } else {
                System.out
                        .println("\nThe Plant " + indicatedPlants.getName() + " is not mature,(growth time > 0)");
            }
        }
    }

    // EFFECT: print the fertilizerStorage Name list
    private void printPlantsSlotStatusList() {
        System.out.println("\n The Slot status:");
        System.out.println("\n-----------------");
        for (int i = 1; i <= coreData.plantsSlot.getStorageSize(); i++) {
            String name = "Plant Name:" + coreData.plantsSlot.getPlants(i - 1).getName() + " Time to grow up: "
                    + coreData.plantsSlot.getPlants(i - 1).getTime();
            System.out.println(name);
        }
        System.out.println("-----------------");
    }

    // EFFECTS: print the FertilizerStorage Name list
    protected void printFertilizerStorageNameList(FertilizerStorage fertilizerStorage) {
        System.out.println("\n The Fertilizer you have:");
        System.out.println("\n-----------------");
        for (int i = 1; i <= fertilizerStorage.getStorage().size(); i++) {
            String name = fertilizerStorage.getNameofi(i - 1);
            System.out.println(name);
        }
        System.out.println("-----------------");
    }

    // EFFECTS: print the PlantsStorage Name list
    private void printPlantsStorageNameList(PlantsStorage plantsStorage) {
        System.out.println("\n The Plants you have:");
        System.out.println("\n-----------------");
        for (int i = 1; i <= plantsStorage.getStorageSize(); i++) {
            String name = plantsStorage.getNameofi(i - 1);
            System.out.println(name);
        }
        System.out.println("-----------------");
    }

    // EFFECTS: print the PlantsStorage Status list
    private void printPlantsStorageStatusList(PlantsStorage plantsStorage) {
        for (int i = 1; i <= plantsStorage.getStorageSize(); i++) {
            String name = plantsStorage.getNameofi(i - 1);
            int price = plantsStorage.geti(i - 1).getPrice();
            int growthTime = plantsStorage.geti(i - 1).getTime();
            System.out.println(name + " its price: " + price + " its growthTime: " + growthTime);
        }
    }

    // EFFECTS: print the FertilizerStorage Status list
    private void printFertilizerStorageStatusList(FertilizerStorage fertilizerStorage) {
        for (int i = 1; i <= fertilizerStorage.getStorage().size(); i++) {
            String name = fertilizerStorage.getNameofi(i - 1);
            int price = fertilizerStorage.geti(i - 1).getPrice();
            int timeReduced = fertilizerStorage.geti(i - 1).getTime();
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
        printPlantsStorageNameList(coreData.plantsStorage);
        System.out.println("\nPlease type the order number of the Plants you want to plant");
        System.out.println("\n type 0 to go back to the upper menu");
    }

    // MODIFIES: this
    // EFFECTS: try to plant Plants from the PlantsStorage
    private void tryPlantPlants(int command) {
        if (command > coreData.plantsStorage.getStorageSize()) {
            System.out.println("Input number is larger than you have");
        } else if (command < 0) {
            System.out.println("Input number cannot be negative");
        } else {
            coreData.plantsSlot.add(coreData.plantsStorage.getStorage().get(command - 1));
            coreData.plantsStorage.remove(command - 1);
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
        printFertilizerStorageNameList(coreData.fertilizerStorage);
        System.out.println("\nPlease type the order number of the Fertilizer you want to use");
        System.out.println("\n type 0 to go back to the upper menu");
    }

    // EFFECTS: verify indicated fertilizer exist
    private boolean validFertilizerCmd(int fertilizerCommand) {
        return ((fertilizerCommand > 0) && (fertilizerCommand <= coreData.fertilizerStorage.getStorage().size()));
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
        if (slotChosenCommand > coreData.plantsSlot.getStorageSize()) {
            System.out.println("Input number is larger than you have");
        } else if (slotChosenCommand < 0) {
            System.out.println("Input number cannot be negative");
        } else {
            int timeReduced = coreData.fertilizerStorage.geti(fertilizerCommand - 1).getTime();
            AgriculturalEntity incdicatedPlants = coreData.plantsSlot.getPlants(slotChosenCommand - 1);
            if (timeReduced > incdicatedPlants.getTime()) {
                incdicatedPlants.setTime(0);
                coreData.fertilizerStorage.remove(fertilizerCommand - 1);
            } else {
                incdicatedPlants.decreaseTime(timeReduced);
                coreData.fertilizerStorage.remove(fertilizerCommand - 1);
            }
            System.out.println("\nApply fertilizer Succussfully!");
            printPlantsSlotStatusList();
        }
    }
}