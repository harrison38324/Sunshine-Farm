package persistence;

import model.FertilizerStorage;
import model.PlantsSlots;
import model.PlantsStorage;
import model.Wallet;

public class LoadGameData {
    private String source;

    public LoadGameData(String source) {
        this.source = source;
    }

    // EFFECTS: load the game
    // data(plantsStorage,fertilizerStorage,wallet,plantsSlot)
    public void loadGameData(Wallet wallet, FertilizerStorage fertilizerStorage, PlantsStorage plantsStorage,
            PlantsSlots plantsSlot) {
        // stub
    }
}
