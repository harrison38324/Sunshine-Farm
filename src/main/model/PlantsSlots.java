package model;

// PlantsSlots is a list of Plants
// Fertilizer can be used for Plants in PlantsSlots
// Mature Plants in the PlantsSlots can be sold
public class PlantsSlots extends PlantsStorage {

    // EFFECTS: the slots for players to plant
    // the Plants they bought
    public PlantsSlots() {
        super();
    }

    // REQUIRES: num >= 0, num < fertilizerStorage.size()
    // EFFECTS: get the Plants in the PlantsSlots
    // with given Index num
    public AgriculturalEntity getPlants(int num) {
        return Storage.get(num);
    }

}
