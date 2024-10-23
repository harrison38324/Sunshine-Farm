package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class TestPlantsSlots {
    private PlantsSlots plantsSlots;
    private Plants norPlants;
    private Plants rarePlants;
    private Plants legendPlants;
    private List<Plants> plantsList;

    @BeforeEach
    void runBefore() {
        plantsSlots = new PlantsSlots();
        norPlants = new Plants("Apple tree", 10, 100);
        rarePlants = new Plants("Blueberry", 100, 200);
        legendPlants = new Plants("gold tree", 1000, 500);

        plantsSlots.add(norPlants);
        plantsSlots.add(rarePlants);
        plantsSlots.add(legendPlants);

        plantsList = new ArrayList<>();
        plantsList.add(norPlants);
        plantsList.add(rarePlants);
        plantsList.add(legendPlants);
    }

    @Test
    void testGetPlants() {
        assertEquals(plantsSlots.getPlants(0), norPlants);
        assertEquals(plantsSlots.getPlants(1), rarePlants);
        assertEquals(plantsSlots.getPlants(2), legendPlants);
    }
}
