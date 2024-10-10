package model;

import java.util.List;
import java.util.ArrayList;

public class PlantsStorage {
    protected List<Plants> plantsStorage;
    private List<String> plantsStorageNameList;

    //EFFECTS: generate a list which stores Plants
    public PlantsStorage(){
        plantsStorage = new ArrayList<>();
        plantsStorageNameList = new ArrayList<>();
    } 

    // MODIFIES: this
    // EFFECTS: add a Plants into plantsStorage
    public void addPlants(Plants plants){
        plantsStorage.add(plants);
    }

    // REQUIRES: num>=0
    // MODIFIES: this
    // EFFECTS: remove a Plants from the Storage
    public void remove(int num){
        plantsStorage.remove(num);
    }

    // EFFECTS: generate a Namelist of the Plants
    //          in the storage in original order
    public List<String> getNameList(){
        for(int i = 0; i < plantsStorage.size();i++){
            plantsStorageNameList.add(plantsStorage.get(i).getPlantsName());
        }
        return plantsStorageNameList;
    }

    public List<Plants> getPlantsStorage(){
        return plantsStorage;
    }
}
