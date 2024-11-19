package model;

import java.util.List;
// import java.util.ArrayList;

// a list of Plants, contains the Plants you bought but haven't planted
public class PlantsStorage extends AgriculturalEntityStorage {
    protected List<Plants> plantsStorage;
    // private List<String> plantsStorageNameList;

    // EFFECTS: generate a list which stores Plants
    public PlantsStorage() {
        super();
    }
}
