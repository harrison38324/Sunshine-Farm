package model;

import java.util.List;

public class PlantsStorage {
    private List<Plants> plantsStorage;

    //EFFECTS: generate a list which stores Plants
    public PlantsStorage(){
        //stub
    } 

    // MODIFIES: this
    // EFFECTS: add a Plants into plantsStorage
    public void addPlants(Plants plants){

    }

    // REQUIRES: num>=0
    // MODIFIES: this
    // EFFECTS: remove a Plants from the Storage
    public void remove(int num){
        //stub
    }

    // EFFECTS: generate a Namelist of the Plants
    //          in the storage in original order
    public List<String> getNameList(){
        //stub
    }

    public List<Plants> getPlantsStorage(){
        //stub
    }
}
