package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestPlantsSlot extends TestPlantsStorage {
    private PlantsSlots plantsSlots;

    @BeforeEach
    void runBefore(){
        plantsSlots = new PlantsSlots();

        plantsSlots.addPlants(norPlants);
        plantsSlots.addPlants(rarePlants);
        plantsSlots.addPlants(legendPlants);
    }

    @Test
    void testGetPlants(){
        assertEquals(plantsSlots.getPlantsStorage(),plantsList2);
    }
}
