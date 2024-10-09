package model;

import java.util.List;

public class FertilizerStorage {
    private List<Fertilizer> fertilizerStorage;

    public FertilizerStorage(){
        //stub
    }

    // MODIFES: this 
    // EFFECTS: add a fertilizer into fertilizerStorage
    public void addFertilizer(Fertilizer fertilizer){

    }

    // REQUIRES: num >= 0
    // MODIFIES: this
    // EFFECTS: remove a fertilizer from the storage
    public void remove(int num){
        //stub
    }

    // EFFECTS: generate a Namelist of the Fertilizer
    //          in the storage in original order.
    public List<String> getNameList(){
        //stub
    }

    public List<Fertilizer> getFertilizerStorage(){
        //stub
    }
}
