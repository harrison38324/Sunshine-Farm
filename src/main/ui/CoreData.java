package ui;

import java.io.IOException;
import java.util.Scanner;

import model.AgriculturalEntity;
import model.Fertilizer;
import model.FertilizerStorage;
import model.Plants;
import model.PlantsSlots;
import model.PlantsStorage;
import model.Wallet;
import model.exceptions.MoneyNotEnoughException;
import model.exceptions.NegativeGrowthTimeException;
import model.exceptions.NotMatureException;
import persistence.LoadGameData;
import persistence.SaveGameData;

// the Core Logic : the core data and methods for Console UI and GUI
public class CoreData {
    protected Scanner input;

    protected AgriculturalEntity selectedFertilizer;
    protected Plants norPlant;
    protected Plants rarePlant;
    protected Plants legendPlant;
    protected Fertilizer norFertilizer;
    protected Fertilizer rareFertilizer;
    protected Fertilizer legendFertilizer;

    protected PlantsStorage plantsCommodity;
    protected FertilizerStorage fertilizerCommodity;
    protected PlantsStorage plantsStorage;
    protected FertilizerStorage fertilizerStorage;

    protected Wallet wallet;

    protected PlantsSlots plantsSlot;

    protected LoadGameData loadGameData;
    protected SaveGameData saveGameData;
    protected static final String JSON_STORE = "./data/gamedata.json";

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
        } else if (timeToGrow > 0) {
            throw new NotMatureException();
        } else {
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
            } else if (agriculturalEntity instanceof Plants) {
                plantsStorage.buyEntity(agriculturalEntity);
            }
        } else {
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
            return true;
        } catch (Exception e) {
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
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
