package model;

import static org.junit.Assert.assertEquals;

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
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getPlantsStorage(), plantsList2);
    }

    @Test
    void testRemove() {
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        plantsStorage.remove(2);
        plantsStorage.remove(1);
        assertEquals(plantsStorage.getPlantsStorage(), plantsList1);
    }

    @Test
    void testGetNameList() {
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getNameList(), plantsNameList);
    }

    @Test
    void testGetPlantsStorage() {
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getPlantsStorage(), plantsList2);
    }

    @Test
    void testGetPlantsStorageSize(){
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getPlantsStorageSize(), 3);
        plantsStorage.remove(1);
        assertEquals(plantsStorage.getPlantsStorageSize(), 2);
    }

    @Test
    void testGetNameofPlantsi(){
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getNameofPlantsi(0), "Apple tree");
        assertEquals(plantsStorage.getNameofPlantsi(1), "Blueberry");
        assertEquals(plantsStorage.getNameofPlantsi(2), "gold tree");
    }

    @Test
    void testgetiPlants(){
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getiPlants(0), norPlants);
        assertEquals(plantsStorage.getiPlants(1), rarePlants);
        assertEquals(plantsStorage.getiPlants(2), legendPlants);
    }


}
