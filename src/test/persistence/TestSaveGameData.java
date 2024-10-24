package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Fertilizer;
import model.FertilizerStorage;
import model.Plants;
import model.PlantsSlots;
import model.PlantsStorage;
import model.Wallet;

public class TestSaveGameData {
    private SaveGameData saveGameData;
    private Wallet norWallet;
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
        norWallet = new Wallet();
        normalFertilizerStorage = new FertilizerStorage();
        normalPlantsStorage = new PlantsStorage();
        normalPlantsSlots = new PlantsSlots();
    }

    @Test
    void testSaveGameDatatoInvalidpath() {
        saveGameData = new SaveGameData("./data/illegal\npath.json");
        try {
            saveGameData.saveGameData(norWallet, normalFertilizerStorage, normalPlantsStorage, normalPlantsSlots);
            fail("Does not throw IOException -- not expected!");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testSaveEmptyGameData() {
        saveGameData = new SaveGameData("./data/testSaveEmptyGameData.json");
        try {
            saveGameData.saveGameData(norWallet, normalFertilizerStorage, normalPlantsStorage, normalPlantsSlots);
            String content = new String(Files.readAllBytes(Paths.get("./data/testSaveEmptyGameData.json")));
            JSONObject gameData = new JSONObject(content);

            assertEquals(gameData.getInt("money"), 0);
            assertEquals(gameData.getJSONArray("fertilizerStorage").length(), 0);
            assertEquals(gameData.getJSONArray("plantsStorage").length(), 0);
            assertEquals(gameData.getJSONArray("plantsSlot").length(), 0);

        } catch (IOException e) {
            fail("IOException is not expected to be thrown");
        }
    }

    @Test
    void testSaveNormalGameData() {
        saveGameData = new SaveGameData("./data/testSaveNormalGameData.json");

        norFertilizer = new Fertilizer("Normal Fertilizer", 10, 20);
        rareFertilizer = new Fertilizer("Rare Fertilizer", 30, 70);
        norPlant = new Plants("Apple", 50, 50);
        legendPlant = new Plants("Golden Apple", 200, 100);
        slotNorPlants = new Plants("Apple", 50, 20);
        slotLegendPlants = new Plants("Golden Apple", 200, 10);

        norWallet.setBalance(200);

        normalFertilizerStorage.add(norFertilizer);
        normalFertilizerStorage.add(rareFertilizer);

        normalPlantsStorage.add(norPlant);
        normalPlantsStorage.add(legendPlant);

        normalPlantsSlots.add(slotNorPlants);
        normalPlantsSlots.add(slotLegendPlants);

        try {
            saveGameData.saveGameData(norWallet, normalFertilizerStorage, normalPlantsStorage, normalPlantsSlots);
            String content = new String(Files.readAllBytes(Paths.get("./data/testSaveNormalGameData.json")));
            JSONObject gameData = new JSONObject(content);
            assertEquals(gameData.getInt("money"), 200);
            assertEquals(gameData.getJSONArray("fertilizerStorage").length(), 2);
            assertEquals(gameData.getJSONArray("plantsStorage").length(), 2);
            assertEquals(gameData.getJSONArray("plantsSlot").length(), 2);

            assertEquals(gameData.getJSONArray("fertilizerStorage").getJSONObject(0).getString("name"), "Normal Fertilizer");
            assertEquals(gameData.getJSONArray("fertilizerStorage").getJSONObject(0).getInt("price"), 10);
            assertEquals(gameData.getJSONArray("fertilizerStorage").getJSONObject(0).getInt("timeReduced"), 20);

            assertEquals(gameData.getJSONArray("plantsStorage").getJSONObject(0).getString("name"), "Apple");
            assertEquals(gameData.getJSONArray("plantsStorage").getJSONObject(0).getInt("price"), 50);
            assertEquals(gameData.getJSONArray("plantsStorage").getJSONObject(0).getInt("growthTime"), 50);
            
            assertEquals(gameData.getJSONArray("plantsSlot").getJSONObject(0).getString("name"), "Apple");
            assertEquals(gameData.getJSONArray("plantsSlot").getJSONObject(0).getInt("price"), 50);
            assertEquals(gameData.getJSONArray("plantsSlot").getJSONObject(0).getInt("growthTime"), 20);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////
            assertEquals(gameData.getJSONArray("fertilizerStorage").getJSONObject(1).getString("name"), "Rare Fertilizer");
            assertEquals(gameData.getJSONArray("fertilizerStorage").getJSONObject(1).getInt("price"), 30);
            assertEquals(gameData.getJSONArray("fertilizerStorage").getJSONObject(1).getInt("timeReduced"), 70);

            assertEquals(gameData.getJSONArray("plantsStorage").getJSONObject(1).getString("name"), "Golden Apple");
            assertEquals(gameData.getJSONArray("plantsStorage").getJSONObject(1).getInt("price"), 200);
            assertEquals(gameData.getJSONArray("plantsStorage").getJSONObject(1).getInt("growthTime"), 100);
            
            assertEquals(gameData.getJSONArray("plantsSlot").getJSONObject(1).getString("name"), "Golden Apple");
            assertEquals(gameData.getJSONArray("plantsSlot").getJSONObject(1).getInt("price"), 200);
            assertEquals(gameData.getJSONArray("plantsSlot").getJSONObject(1).getInt("growthTime"), 10);
        } catch (IOException e) {
            fail("Throw IOException- not expected!");
        }
    }
    
}
