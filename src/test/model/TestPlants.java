package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlants {
    private Plants plants;
    private Fertilizer fertilizer;

    @BeforeEach
    void runBefore() {
        plants = new Plants("Apple tree", 10, 3);
        fertilizer = new Fertilizer("Normal fertilizer", 10, 1);
    }

    @Test
    void testgetPrice() {
        assertEquals(plants.getPrice(), 10);
    }

    @Test
    void testgetPlantsName() {
        assertEquals(plants.getName(), "Apple tree");
    }

    @Test
    void testgetGrowthTime() {
        assertEquals(plants.getTime(), 3);
    }

    @Test
    void testincreasePrice() {
        assertEquals(plants.getPrice(), 10);
        plants.increasePrice(1);
        assertEquals(plants.getPrice(), 11);
    }

    @Test
    void testincreasePriceMutipleTimes() {
        assertEquals(plants.getPrice(), 10);
        plants.increasePrice(2);
        assertEquals(plants.getPrice(), 12);
        plants.increasePrice((10));
        assertEquals(plants.getPrice(), 22);
        plants.increasePrice(7);
        assertEquals(plants.getPrice(), 29);
    }

    @Test
    void testdecreasePrice() {
        assertEquals(plants.getPrice(), 10);
        plants.decreasePrice(1);
        assertEquals(plants.getPrice(), 9);
    }

    @Test
    void testdecreasePriceMutipleTimes() {
        assertEquals(plants.getPrice(), 10);
        plants.decreasePrice(2);
        assertEquals(plants.getPrice(), 8);
        plants.decreasePrice(5);
        assertEquals(plants.getPrice(), 3);
        plants.decreasePrice(2);
        assertEquals(plants.getPrice(), 1);
    }

    @Test
    void testdecreaseGrowthTime() {
        assertEquals(plants.getTime(), 3);
        plants.decreaseTime(1);
        assertEquals(plants.getTime(), 2);
    }

    @Test
    void testdecreaseGrowthTimeMutipleTimes() {
        assertEquals(plants.getTime(), 3);
        plants.decreaseTime(1);
        assertEquals(plants.getTime(), 2);
        plants.decreaseTime(2);
        assertEquals(plants.getTime(), 0);
    }

    @Test
    void testuseFertilizer() {
        plants.useFertilizer(fertilizer);
        assertEquals(plants.getTime(), 2);
    }

    @Test
    void testuseFertilizerMutipleTimes() {
        plants.useFertilizer(fertilizer);
        assertEquals(plants.getTime(), 2);
        plants.useFertilizer(fertilizer);
        assertEquals(plants.getTime(), 1);
        plants.useFertilizer(fertilizer);
        assertEquals(plants.getTime(), 0);
    }

    @Test
    void testSetGrowthTime() {
        plants.setTime(0);
        assertEquals(plants.getTime(), 0);
        plants.setTime(12);
        assertEquals(plants.getTime(), 12);
    }
}
