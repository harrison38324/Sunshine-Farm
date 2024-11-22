package ui;

import java.util.Scanner;

import exceptions.NegativeGrowthTimeException;
import exceptions.NotMatureException;
import model.*;

// the ui for the whole application
// have functions for all the application
// have farm menu which you can plant Plants,use Fertillizer,check status of the farm, sell mature plants
// have market menu which you can buy agricultural entities
public class ConsoleUI {
    private CoreData coreData;
    private Scanner input;

    // EFFECTS: run run the Sunshine Farm application
    public ConsoleUI(CoreData coreData) {
        this.coreData = coreData;
        runProgram();
    }

    // EFFECTS: process user input
    private void runProgram() {
        boolean keepGoing = true;
        String command = null;

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

    // EFFECTS: choose sub Menu based on the user's input command
    private void chooseSubMenu(String command) {
        if (command.equals("f")) {
            farmMenu();
        } else if (command.equals("m")) {
            marketMenu();
        } else if (command.equals("l")) {
            loadGameData();
        } else if (command.equals("s")) {
            saveGameData();
        } else {
            System.out.println("Input is Not a valid value...");
        }
    }

    // MODIFES: coreData
    // EFFECTS: load game data
    private void loadGameData() {
        boolean isSucess = coreData.loadGameData();
        if (isSucess) {
            printLoadedData();
        } else {
            System.out.println("Fail to load Game Data");
        }
    }

    // MODIFES: coreData
    // EFFECTS: save game data
    private void saveGameData() {
        boolean isSucess = coreData.saveGameData();
        if (isSucess) {
            System.out.println("Save successfully!");
        } else {
            System.out.println("Fail to save Game Data");
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
                selectCommodityCategory(command);
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

    // EFFECTS: select the commodity Category user want to buy
    private void selectCommodityCategory(String command) {
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

    // EFFECTS: valid the user's input value to be int
    private int validInputInt() {
        while (!input.hasNextInt()) {
            System.out.println("That's not a number!");
            input.next();
        }
        return input.nextInt();
    }

    // MODIFIES: coreData
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

    // MODIFIES: coreData
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

    // MODIFIES: coreData
    // EFFECTS: try to sell the Plants
    private void trySellPlants(int command) {
        if (command > coreData.plantsSlot.getStorageSize()) {
            System.out.println("\nInput number is larger than you have");
        } else if (command < 0) {
            System.out.println("\nInput number cannot be negative");
        } else {
            AgriculturalEntity indicatedPlants = coreData.plantsSlot.getPlants(command - 1);
            int price = indicatedPlants.getPrice();
            try {
                coreData.sellMaturePlants(indicatedPlants);
                System.out.println("\nYou sold " + indicatedPlants.getName() + " and get " + price * 2);
            } catch (NotMatureException e) {
                System.out
                        .println("\nThe Plant " + indicatedPlants.getName() + " is not mature,(growth time > 0)");
            } catch (NegativeGrowthTimeException e) {
                System.out.println("ERROR the time to grow up is negative!");
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
    private void printFertilizerStorageNameList(FertilizerStorage fertilizerStorage) {
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

    // MODIFIES: coreData
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

    // MODIFIES: coreData
    // EFFECTS: try to plant Plants from the PlantsStorage
    private void tryPlantPlants(int command) {
        if (command > coreData.plantsStorage.getStorageSize()) {
            System.out.println("Input number is larger than you have");
        } else if (command < 0) {
            System.out.println("Input number cannot be negative");
        } else {
            coreData.plantPlants(coreData.plantsStorage.geti(command - 1));
            System.out.println("\nPlant successfully!");
        }
    }

    // MODIFIES: coreData
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
    // MODIFIES: coreData
    // EFFECTS: try to apply the fertilizer to the plant that user indicated
    private void tryApplyFertilizer(int slotChosenCommand, int fertilizerCommand) {
        if (slotChosenCommand > coreData.plantsSlot.getStorageSize()) {
            System.out.println("Input number is larger than you have");
        } else if (slotChosenCommand < 0) {
            System.out.println("Input number cannot be negative");
        } else {
            AgriculturalEntity selectedFertilizer = coreData.fertilizerStorage.geti(fertilizerCommand - 1);
            AgriculturalEntity selectedPlant = coreData.plantsSlot.getPlants(slotChosenCommand - 1);
            coreData.applyFertilizer(selectedFertilizer, selectedPlant);
            System.out.println("\nApply fertilizer Succussfully!");
            printPlantsSlotStatusList();
        }
    }
}