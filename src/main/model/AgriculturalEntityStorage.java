package model;

import java.util.List;
import java.util.ArrayList;

// the storage of AgriculturalEntity, a list of the entities
public class AgriculturalEntityStorage {
    protected List<AgriculturalEntity> storage;
    protected List<String> storageNameList;

    // EFFECTS: generate a list which stores Plants
    public AgriculturalEntityStorage() {
        storage = new ArrayList<>();
        storageNameList = new ArrayList<>();
    }

    // EFFECTS: generate a Namelist of the Plants
    // in the storage in original order
    public List<String> getNameList() {
        for (int i = 0; i < storage.size(); i++) {
            storageNameList.add(storage.get(i).getName());
        }
        return storageNameList;
    }

    public List<AgriculturalEntity> getStorage() {
        return storage;
    }

    public int getStorageSize() {
        return storage.size();
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
        storage.add(boughtEntity);
    }

    // MODIFIES: this
    // EFFECTS: add a AgriculturalEntity into plantsStorage
    public void add(AgriculturalEntity entity) {
        storage.add(entity);
    }

    // REQUIRES: num>=0
    // MODIFIES: this
    // EFFECTS: remove a AgriculturalEntity in index num from the Storage
    public void remove(int num) {
        storage.remove(num);
    }

    // REQUIRES: agriculturalEntityStorage contains agricultureEntity
    // MODIFISE: this
    // EFFECTS: remove the given agriculturalEntity from agriculturalEntityStorage
    public void remove(AgriculturalEntity agriculturalEntity){
        storage.remove(agriculturalEntity);
    }

    // MODIFIES: this
    // EFFECTS: clear the Storage
    public void clear() {
        storage.clear();
    }
}
