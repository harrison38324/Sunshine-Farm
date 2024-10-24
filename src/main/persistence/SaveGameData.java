package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import model.FertilizerStorage;
import model.PlantsSlots;
import model.PlantsStorage;
import model.Wallet;

import java.io.FileWriter;
import java.io.IOException;


// a class for saving Game data
public class SaveGameData {
    private String source;

    public SaveGameData(String source) {
        this.source = source;
    }

    // EFFECTS: save the game
    // data(plantsStorage,fertilizerStorage,wallet,plantsSlot)
    public void saveGameData(Wallet wallet, FertilizerStorage fertilizerStorage, PlantsStorage plantsStorage,
            PlantsSlots plantsSlot) throws IOException {
        // stub
    }

}
