package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AgriculturalEntity;
import model.AgriculturalEntityStorage;
import model.Fertilizer;
import model.FertilizerStorage;
import model.Plants;
import model.PlantsSlots;
import model.PlantsStorage;
import model.Wallet;

public class TestLoadGameData {
    private Wallet testWallet;
    private FertilizerStorage testFertilizerStorage;
    private PlantsStorage testPlantsStorage;
    private PlantsSlots testPlantsSlots;

    private FertilizerStorage normalFertilizerStorage;
    private PlantsStorage normalPlantsStorage;
    private PlantsSlots normalPlantsSlots;

    private Fertilizer norFertilizer;
    private Fertilizer rareFertilizer;
    private Plants norPlant;
    private Plants legendPlant;
    private Plants slotNorPlants;
    private Plants slotLegendPlants;

    @BeforeEach
    void runBefore() {
        testWallet = new Wallet();
        testFertilizerStorage = new FertilizerStorage();
        testPlantsStorage = new PlantsStorage();
        testPlantsSlots = new PlantsSlots();

        normalFertilizerStorage = new FertilizerStorage();
        normalPlantsStorage = new PlantsStorage();
        normalPlantsSlots = new PlantsSlots();
    }

    @Test
    void testLoadNonExistentFile() {
        LoadGameData data = new LoadGameData("./data/noSuchFile.json");
        try {
            data.loadGameData(testWallet, testFertilizerStorage, testPlantsStorage, testPlantsSlots);
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testLoadNormalGameData() {
        norFertilizer = new Fertilizer("Normal Fertilizer", 10, 20);
        rareFertilizer = new Fertilizer("Rare Fertilizer", 30, 70);
        norPlant = new Plants("Apple", 50, 50);
        legendPlant = new Plants("Golden Apple", 200, 100);
        slotNorPlants = new Plants("Apple", 50, 20);
        slotLegendPlants = new Plants("Golden Apple", 200, 10);

        normalFertilizerStorage.add(norFertilizer);
        normalFertilizerStorage.add(rareFertilizer);

        normalPlantsStorage.add(norPlant);
        normalPlantsStorage.add(legendPlant);

        normalPlantsSlots.add(slotNorPlants);
        normalPlantsSlots.add(slotLegendPlants);

        LoadGameData data = new LoadGameData("./data/testLoadNormalGameData.json");
        try {
            data.loadGameData(testWallet, testFertilizerStorage, testPlantsStorage, testPlantsSlots);
            assertEquals(testWallet.getBalance(), 1000);
            helperTestData(testFertilizerStorage,normalFertilizerStorage);
            helperTestData(testPlantsStorage,normalPlantsStorage);
            helperTestData(testPlantsSlots,normalPlantsSlots);

        } catch (IOException e) {
            fail("Throw IOException- not expected!");
        }
    }

    // EFFECTS: help checkk every value in the Game data
    public void helperTestData(AgriculturalEntityStorage testStorage,AgriculturalEntityStorage norStorage){
        int i = 0;
            for(AgriculturalEntity agriculturalEntity:testStorage.getStorage()){
                assertEquals(agriculturalEntity.getName(), norStorage.geti(i).getName());
                assertEquals(agriculturalEntity.getPrice(), norStorage.geti(i).getPrice());
                assertEquals(agriculturalEntity.getTime(), norStorage.geti(i).getTime());
                i++;
            }
    }

    @Test
    void testLoadEmptyGameData() {
        LoadGameData data = new LoadGameData("./data/testLoadEmptyGameData.json");
        try{
            data.loadGameData(testWallet, testFertilizerStorage, testPlantsStorage, testPlantsSlots);
            assertEquals(testWallet.getBalance(), 1000);
            assertEquals(normalFertilizerStorage.getStorage(), testFertilizerStorage.getStorage());
            assertEquals(normalPlantsStorage.getStorage(), testPlantsStorage.getStorage());
            assertEquals(normalPlantsSlots.getStorage(),testPlantsStorage.getStorage());
        } catch(IOException e) {
            fail("Throw IOException- not expected!");
        }
    }
}
