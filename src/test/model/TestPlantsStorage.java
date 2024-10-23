package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestPlantsStorage {
    private PlantsStorage plantsStorage;
    private Plants norPlants;
    private Plants rarePlants;
    private Plants legendPlants;
    private List<Plants> plantsList1;
    private List<Plants> plantsList2;
    private List<String> plantsNameList;

    @BeforeEach
    void runBefore() {
        plantsStorage = new PlantsStorage();
        norPlants = new Plants("Apple tree", 10, 100);
        rarePlants = new Plants("Blueberry", 100, 200);
        legendPlants = new Plants("gold tree", 1000, 500);

        plantsList1 = new ArrayList<>();
        plantsList2 = new ArrayList<>();

        plantsList1.add(norPlants);

        plantsList2.add(norPlants);
        plantsList2.add(rarePlants);
        plantsList2.add(legendPlants);

        plantsNameList = new ArrayList<>();
        plantsNameList.add("Apple tree");
        plantsNameList.add("Blueberry");
        plantsNameList.add("gold tree");
    }

    @Test
    void testAddPlants() {
        plantsStorage.add(norPlants);
        plantsStorage.add(rarePlants);
        plantsStorage.add(legendPlants);
        assertEquals(plantsStorage.getStorage(), plantsList2);
    }

    @Test
    void testRemove() {
        plantsStorage.add(norPlants);
        plantsStorage.add(rarePlants);
        plantsStorage.add(legendPlants);
        plantsStorage.remove(2);
        plantsStorage.remove(1);
        assertEquals(plantsStorage.getStorage(), plantsList1);
    }

    @Test
    void testGetNameList() {
        plantsStorage.add(norPlants);
        plantsStorage.add(rarePlants);
        plantsStorage.add(legendPlants);
        assertEquals(plantsStorage.getNameList(), plantsNameList);
    }

    @Test
    void testGetPlantsStorage() {
        plantsStorage.add(norPlants);
        plantsStorage.add(rarePlants);
        plantsStorage.add(legendPlants);
        assertEquals(plantsStorage.getStorage(), plantsList2);
    }

    @Test
    void testGetPlantsStorageSize(){
        plantsStorage.add(norPlants);
        plantsStorage.add(rarePlants);
        plantsStorage.add(legendPlants);
        assertEquals(plantsStorage.getStorageSize(), 3);
        plantsStorage.remove(1);
        assertEquals(plantsStorage.getStorageSize(), 2);
    }

    @Test
    void testGetNameofPlantsi(){
        plantsStorage.add(norPlants);
        plantsStorage.add(rarePlants);
        plantsStorage.add(legendPlants);
        assertEquals(plantsStorage.getNameofi(0), "Apple tree");
        assertEquals(plantsStorage.getNameofi(1), "Blueberry");
        assertEquals(plantsStorage.getNameofi(2), "gold tree");
    }

    @Test
    void testgetiPlants(){
        plantsStorage.add(norPlants);
        plantsStorage.add(rarePlants);
        plantsStorage.add(legendPlants);
        assertEquals(plantsStorage.geti(0), norPlants);
        assertEquals(plantsStorage.geti(1), rarePlants);
        assertEquals(plantsStorage.geti(2), legendPlants);
    }

    @Test
    void testbuyPlants(){
        plantsStorage.buyEntity(norPlants);
        assertFalse(plantsStorage.geti(0) == norPlants);
        assertEquals(plantsStorage.geti(0).getName(), norPlants.getName());
        assertEquals(plantsStorage.geti(0).getTime(), norPlants.getTime());
        assertEquals(plantsStorage.geti(0).getPrice(), norPlants.getPrice());
        plantsStorage.buyEntity(norPlants);
        assertFalse(plantsStorage.geti(1) == norPlants);
        assertEquals(plantsStorage.geti(1).getName(), norPlants.getName());
        assertEquals(plantsStorage.geti(1).getTime(), norPlants.getTime());
        assertEquals(plantsStorage.geti(1).getPrice(), norPlants.getPrice());
        plantsStorage.geti(1).decreaseTime(10);
        assertEquals(plantsStorage.geti(0).getTime(), 100);
        assertEquals(plantsStorage.geti(1).getTime(), 90);
    }
}
