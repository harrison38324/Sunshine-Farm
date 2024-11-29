package model;

import java.io.IOException;
import java.util.Scanner;

import exceptions.MoneyNotEnoughException;
import exceptions.NegativeGrowthTimeException;
import exceptions.NotMatureException;
import persistence.LoadGameData;
import persistence.SaveGameData;

// the Core Data : the core data and methods for Console UI and GUI
public class CoreData {
    private Scanner input;

    private AgriculturalEntity selectedFertilizer;
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

    private LoadGameData loadGameData;
    private SaveGameData saveGameData;
    private static final String JSON_STORE = "./data/gamedata.json";

    // EFFECTS: the game data and the core behavior about the game data
    public CoreData() {
        selectedFertilizer = null;
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

        plantsCommodity.add(norPlant);
        plantsCommodity.add(rarePlant);
        plantsCommodity.add(legendPlant);

        fertilizerCommodity.add(norFertilizer);
        fertilizerCommodity.add(rareFertilizer);
        fertilizerCommodity.add(legendFertilizer);

        plantsStorage.buyEntity(norPlant);
        fertilizerStorage.add(norFertilizer);
        fertilizerStorage.add(norFertilizer);

        wallet.earn(1000);
    }

    // REQUIRES: plant is Plant, plant given is in the PlantsStorage
    // MODIFISE: this
    // EFFECTS: plant Plants to the Plants Slot
    public void plantPlants(AgriculturalEntity plant) {
        plantsStorage.remove(plant);
        plantsSlot.add(plant);
        EventLog.getInstance().logEvent(
                new Event("Plant " + plant.getName() + " to the plots"));
    }

    // REQUIRES: selectedFertilizer is in the fertilizerStorage
    // MODIFIES: this
    // EFFECTS: apply fertilizer to that plant and remove it from the
    // fertilizerStorage
    // if the fertilizer' time reduce is larger than the time that the selected
    // plant needed
    // to grow up, set the time to grow up to zero
    public void applyFertilizer(AgriculturalEntity selectedFertilizer, AgriculturalEntity selectedPlant) {
        int timeReduced = selectedFertilizer.getTime();
        int timeToGrow = selectedPlant.getTime();
        if (timeReduced >= timeToGrow) {
            selectedPlant.setTime(0);
        } else {
            selectedPlant.decreaseTime(timeReduced);
        }
        fertilizerStorage.remove(selectedFertilizer);
        EventLog.getInstance().logEvent(
                new Event("Apply " + selectedFertilizer.getName() + " to " + selectedPlant.getName()));
    }

    // REQUIRES: indicatedPlant should in plantsSlot
    // MODIFES: this
    // EFFECTS: if the selected plants in slot is mature(time to growth = 0), sell
    // it and get money, remove it from plot, if the growth time >0, throw
    // NotMatureException()
    // if growth time <0, throw egativeGrowthTimeException()
    public void sellMaturePlants(AgriculturalEntity indicatedPlant)
            throws NotMatureException, NegativeGrowthTimeException {
        int timeToGrow = indicatedPlant.getTime();
        int price = indicatedPlant.getPrice();
        if (timeToGrow == 0) {
            plantsSlot.remove(indicatedPlant);
            wallet.earn(price * 2);
            EventLog.getInstance().logEvent(
                    new Event("Mature plant " + indicatedPlant.getName() + " sold"));
        } else if (timeToGrow > 0) {
            EventLog.getInstance().logEvent(
                    new Event("Try to sell " + indicatedPlant.getName() + " But fail because it is not mature"));
            throw new NotMatureException();
        } else {
            EventLog.getInstance().logEvent(
                    new Event("Try to sell " + indicatedPlant.getName()
                            + " But fail because it has negative growth time"));
            throw new NegativeGrowthTimeException();
        }
    }

    // MODIFIES: this
    // EFFECTS: try to buy one item, if the money is enough, other wise throw
    // MoneyNotEnoughException()
    public void tryToBuyCommodity(AgriculturalEntity agriculturalEntity) throws MoneyNotEnoughException {
        int price = agriculturalEntity.getPrice();
        int saving = wallet.getBalance();
        if (price <= saving) {
            wallet.spend(price);
            if (agriculturalEntity instanceof Fertilizer) {
                fertilizerStorage.buyEntity(agriculturalEntity);
                EventLog.getInstance().logEvent(
                        new Event("Bought Fertilizer: " + agriculturalEntity.getName()));
            } else if (agriculturalEntity instanceof Plants) {
                plantsStorage.buyEntity(agriculturalEntity);
                EventLog.getInstance().logEvent(
                        new Event("Bought Plant: " + agriculturalEntity.getName()));
            }
        } else {
            EventLog.getInstance().logEvent(
                    new Event("Try to buy " + agriculturalEntity.getName() + " But fail because not enough balance"));
            throw new MoneyNotEnoughException();
        }
    }

    // MODIFES: coreData
    // EFFECTS: load game data, return true if load successfully, else return false
    public boolean loadGameData() {
        loadGameData = new LoadGameData(JSON_STORE);
        try {
            loadGameData.loadGameData(wallet, fertilizerStorage,
                    plantsStorage, plantsSlot);
            EventLog.getInstance().logEvent(
                    new Event("Load Game data from " + JSON_STORE));
            return true;
        } catch (IOException e) {
            EventLog.getInstance().logEvent(
                    new Event("Try to load Game data but encounter error: IOException"));
            return false;
        }
    }

    // MODIFES: JSON_STORE
    // EFFECTS: save game data,return true if save successfully, else return false
    public boolean saveGameData() {
        saveGameData = new SaveGameData(JSON_STORE);
        try {
            saveGameData.saveGameData(wallet, fertilizerStorage,
                    plantsStorage, plantsSlot);
            EventLog.getInstance().logEvent(
                    new Event("Save Game data at " + JSON_STORE));
            return true;
        } catch (IOException e) {
            EventLog.getInstance().logEvent(
                    new Event("Save to load Game data but encounter error: IOException"));
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: set the selected fertilizer to null
    public void unSelectFertilizer() {
        selectedFertilizer = null;
    }

    // MODIFIES: this
    // EFFECT: set the selectedFertilizer to the given one
    public void selectFertilizer(AgriculturalEntity fertilizer) {
        selectedFertilizer = fertilizer;
    }

    public Scanner getInput() {
        return input;
    }

    public AgriculturalEntity getSelectedFertilizer() {
        return selectedFertilizer;
    }

    public Plants getNorPlant() {
        return norPlant;
    }

    public Plants getRarePlant() {
        return rarePlant;
    }

    public Plants getLegendPlant() {
        return legendPlant;
    }

    public Fertilizer getNorFertilizer() {
        return norFertilizer;
    }

    public Fertilizer getRareFertilizer() {
        return rareFertilizer;
    }

    public Fertilizer getLegendFertilizer() {
        return legendFertilizer;
    }

    public PlantsStorage getPlantsCommodity() {
        return plantsCommodity;
    }

    public FertilizerStorage getFertilizerCommodity() {
        return fertilizerCommodity;
    }

    public PlantsStorage getPlantsStorage() {
        return plantsStorage;
    }

    public FertilizerStorage getFertilizerStorage() {
        return fertilizerStorage;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public PlantsSlots getPlantsSlot() {
        return plantsSlot;
    }

    public LoadGameData getLoadGameData() {
        return loadGameData;
    }

    public SaveGameData getSaveGameData() {
        return saveGameData;
    }

    public static String getJsonStore() {
        return JSON_STORE;
    }

}
