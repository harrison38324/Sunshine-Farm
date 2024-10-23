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
        // plantsStorage = new ArrayList<>();
        // plantsStorageNameList = new ArrayList<>();
    }

    // // MODIFIES: this
    // // EFFECTS: add a Plants into plantsStorage
    // public void addPlants(Plants plants) {
    // plantsStorage.add(plants);
    // }

    // // REQUIRES: num>=0
    // // MODIFIES: this
    // // EFFECTS: remove a Plants in index num from the Storage
    // public void remove(int num) {
    // plantsStorage.remove(num);
    // }

    // // EFFECTS: generate a Namelist of the Plants
    // // in the storage in original order
    // public List<String> getNameList() {
    // for (int i = 0; i < plantsStorage.size(); i++) {
    // plantsStorageNameList.add(plantsStorage.get(i).getName());
    // }
    // return plantsStorageNameList;
    // }

    // public List<Plants> getPlantsStorage() {
    // return plantsStorage;
    // }

    // public int getPlantsStorageSize() {
    // return plantsStorage.size();
    // }

    // // EFFECTS: get the Plants with index in plantsStorage
    // public Plants getiPlants(int i) {
    // return getPlantsStorage().get(i);
    // }

    // // EFFECTS: get the Name of index i of the plantsStorage
    // public String getNameofPlantsi(int i) {
    // return getPlantsStorage().get(i).getName();
    // }

    // // EFFETCS: dupilicate the indicated Plants and add them into the
    // plantsStorage
    // public void buyPlants(Plants indicatePlants) {
    // Plants boughtPlants = new Plants(indicatePlants.getName(),
    // indicatePlants.getPrice(), indicatePlants.getTime());
    // addPlants(boughtPlants);
    // }
}
