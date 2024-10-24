package persistence;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.AgriculturalEntity;
import model.AgriculturalEntityStorage;
import model.FertilizerStorage;
import model.PlantsSlots;
import model.PlantsStorage;
import model.Wallet;

// the class used for Game Data loading
public class LoadGameData {
    private String source;
    private JSONObject gameData;

    public LoadGameData(String source) {
        this.source = source;
    }

    // MODIFIES: this
    // EFFECTS: load AgriculturalEntityStorage Data
    private void loadAgriculturalEntityStorageData(AgriculturalEntityStorage agriculturalEntityStorage,
            String storageName, String timeName) {
        JSONArray agriculturalEntityStorageArray = gameData.getJSONArray(storageName);
        agriculturalEntityStorage.clear();
        for (int i = 0; i < agriculturalEntityStorageArray.length(); i++) {
            JSONObject newAgriculturalEntity = agriculturalEntityStorageArray.getJSONObject(i);
            String name = newAgriculturalEntity.getString("name");
            int price = newAgriculturalEntity.getInt("price");
            int time = newAgriculturalEntity.getInt(timeName);
            AgriculturalEntity gotAgriculturalEntity = new AgriculturalEntity(name, price, time);
            agriculturalEntityStorage.add(gotAgriculturalEntity);
        }
    }

    // MODIFIES: this
    // EFFECTS: load the game
    // data(plantsStorage,fertilizerStorage,wallet,plantsSlot)
    public void loadGameData(Wallet wallet, FertilizerStorage fertilizerStorage, PlantsStorage plantsStorage,
            PlantsSlots plantsSlot) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(source)));
        gameData = new JSONObject(content);

        int moneyData = gameData.getInt("money");
        wallet.setBalance(moneyData);
        loadAgriculturalEntityStorageData(fertilizerStorage, "fertilizerStorage", "timeReduced");
        loadAgriculturalEntityStorageData(plantsStorage, "plantsStorage", "growthTime");
        loadAgriculturalEntityStorageData(plantsSlot, "plantsSlot", "growthTime");
    }
}
