package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestPlantsStorage {
    public PlantsStorage plantsStorage;
    protected Plants norPlants;
    protected Plants rarePlants;
    protected Plants legendPlants;
    private List<Plants> plantsList1;
    protected List<Plants> plantsList2;
    private List<String> plantsNameList;

    @BeforeEach
    void runBefore(){
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
    void testAddPlants(){
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getPlantsStorage(),plantsList2);
    }

    @Test
    void testRemove(){
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        plantsStorage.remove(2);
        plantsStorage.remove(1);
        assertEquals(plantsStorage.getPlantsStorage(), plantsList1);
    }

    @Test
    void testGetNameList(){
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getNameList(),plantsNameList);
    }

    @Test
    void testGetPlantsStorage(){
        plantsStorage.addPlants(norPlants);
        plantsStorage.addPlants(rarePlants);
        plantsStorage.addPlants(legendPlants);
        assertEquals(plantsStorage.getPlantsStorage(),plantsList2);
    }
}