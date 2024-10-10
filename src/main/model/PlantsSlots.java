package model;

public class PlantsSlots extends PlantsStorage {

    // EFFECTS: the slots for players to plant
    //          the Plants they bought
    public PlantsSlots(){
        super();
    }

    // REQUIRES: num >= 0, num < fertilizerStorage.size()
    // EFFECTS: get the Plants in the PlantsSlots 
    //          with given Index num
    public Plants getPlants(int num){
        return plantsStorage.get(num);
    }

}
