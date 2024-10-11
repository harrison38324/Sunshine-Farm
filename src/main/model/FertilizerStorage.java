package model;

import java.util.List;
import java.util.ArrayList;

public class FertilizerStorage {
    private List<Fertilizer> fertilizerStorage;
    private List<String> fertilizerNameList;

    // EFFECTS: generate a list which stores fertilizers
    public FertilizerStorage() {
        fertilizerStorage = new ArrayList<>();
        fertilizerNameList = new ArrayList<>();
    }

    // MODIFES: this
    // EFFECTS: add a fertilizer into fertilizerStorage
    public void addFertilizer(Fertilizer fertilizer) {
        fertilizerStorage.add(fertilizer);
    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: remove a fertilizer from the storage
    public void remove(int num) {
        fertilizerStorage.remove(num);
    }

    // EFFECTS: generate a Namelist of the Fertilizer
    // in the storage in original order.
    public List<String> getNameList() {
        for (int i = 0; i < fertilizerStorage.size(); i++) {
            fertilizerNameList.add(fertilizerStorage.get(i).getName());
        }
        return fertilizerNameList;
    }

    public List<Fertilizer> getFertilizerStorage() {
        return fertilizerStorage;
    }

    // EFFECTS: get the Fertilizer of index i of the fertilizerStorage
    public Fertilizer getiFertilizer(int i) {
        return getFertilizerStorage().get(i);
    }

    // EFFECTS: get the Name of index i of the fertilizerStorage
    public String getNameofFertilizeri(int i) {
        return getFertilizerStorage().get(i).getName();
    }

    // EFFETCS: dupilicate the indicated Fertilizer and add them into the
    // fertilizerStorage
    public void buyFertilizer(Fertilizer indicateFertilizer) {
        Fertilizer boughtFertilizer = new Fertilizer(indicateFertilizer.getName(),
                indicateFertilizer.getPrice(), indicateFertilizer.getTimeReduced());
        addFertilizer(boughtFertilizer);
    }
}
