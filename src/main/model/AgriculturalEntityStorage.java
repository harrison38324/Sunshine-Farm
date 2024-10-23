package model;

import java.util.List;
import java.util.ArrayList;

public class AgriculturalEntityStorage {
    protected List<AgriculturalEntity> Storage;
    protected List<String> StorageNameList;

    // EFFECTS: generate a list which stores Plants
    public AgriculturalEntityStorage() {
        Storage = new ArrayList<>();
        StorageNameList = new ArrayList<>();
    }

    // EFFECTS: generate a Namelist of the Plants
    // in the storage in original order
    public List<String> getNameList() {
        for (int i = 0; i < Storage.size(); i++) {
            StorageNameList.add(Storage.get(i).getName());
        }
        return StorageNameList;
    }

    public List<AgriculturalEntity> getStorage() {
        return Storage;
    }

    public int getStorageSize() {
        return Storage.size();
    }

    // EFFECTS: get the Plants with index in Storage
    public AgriculturalEntity geti(int i) {
        return getStorage().get(i);
    }

    // EFFECTS: get the Name of index i of the Storage
    public String getNameofi(int i) {
        return getStorage().get(i).getName();
    }

    // EFFETCS: dupilicate the indicated Plants and add them into the plantsStorage
    public void buyEntity(AgriculturalEntity indicateEntity) {
        AgriculturalEntity boughtEntity = new AgriculturalEntity(indicateEntity.getName(),
        indicateEntity.getPrice(), indicateEntity.getTime());
        Storage.add(boughtEntity);
    }

    // MODIFIES: this
    // EFFECTS: add a AgriculturalEntity into plantsStorage
    public void add(AgriculturalEntity entity) {
        Storage.add(entity);
    }

    // REQUIRES: num>=0
    // MODIFIES: this
    // EFFECTS: remove a AgriculturalEntity in index num from the Storage
    public void remove(int num) {
        Storage.remove(num);
    }
}
