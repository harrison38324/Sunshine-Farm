package persistence;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import model.AgriculturalEntity;
import model.AgriculturalEntityStorage;
import model.FertilizerStorage;
import model.PlantsSlots;
import model.PlantsStorage;
import model.Wallet;

// a class for saving Game data
public class SaveGameData {
    private String source;
    private FileWriter file;
    private JSONObject gameData;

    public SaveGameData(String source) {
        this.source = source;
    }

    // MODIFIES: this
    // EFFECTS: save the AgriculturalEntity data
    private void saveAgriculturalEntityData(AgriculturalEntityStorage agriculturalEntityStorage, JSONObject gameData,
            String storageName, String timeName) {
        JSONArray agriculturalEntityArray = new JSONArray();
        for (AgriculturalEntity agriculturalEntity : agriculturalEntityStorage.getStorage()) {
            JSONObject newAgriculturalEnity = new JSONObject();
            newAgriculturalEnity.put("name", agriculturalEntity.getName());
            newAgriculturalEnity.put("price", agriculturalEntity.getPrice());
            newAgriculturalEnity.put(timeName, agriculturalEntity.getTime());
            agriculturalEntityArray.put(newAgriculturalEnity);
        }
        gameData.put(storageName, agriculturalEntityArray);
    }

    // MODIFIES: this
    // EFFECTS: save the game
    // data(plantsStorage,fertilizerStorage,wallet,plantsSlot)
    public void saveGameData(Wallet wallet, FertilizerStorage fertilizerStorage, PlantsStorage plantsStorage,
            PlantsSlots plantsSlot) throws IOException {
        gameData = new JSONObject();

        gameData.put("money", wallet.getBalance());

        saveAgriculturalEntityData(fertilizerStorage, gameData, "fertilizerStorage", "timeReduced");
        saveAgriculturalEntityData(plantsStorage, gameData, "plantsStorage", "growthTime");
        saveAgriculturalEntityData(plantsSlot, gameData, "plantsSlot", "growthTime");

        file = new FileWriter(source);
        file.write(gameData.toString(4));
        file.close();
    }

}
