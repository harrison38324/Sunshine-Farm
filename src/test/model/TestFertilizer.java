package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFertilizer {
    private Fertilizer fertilizer;

    @BeforeEach
    void runBefore() {
        fertilizer = new Fertilizer("Normal Fertilizer", 10, 1);
    }

    @Test
    void testGetPrice() {
        assertEquals(fertilizer.getPrice(), 10);
    }

    @Test
    void testGetName() {
        assertEquals(fertilizer.getName(), "Normal Fertilizer");
    }

    @Test
    void testGetTimeReduced() {
        assertEquals(fertilizer.getTimeReduced(), 1);
    }

    @Test
    void testIncreasePrice() {
        fertilizer.increasePrice(10);
        assertEquals(fertilizer.getPrice(), 20);
        fertilizer.increasePrice(2);
        assertEquals(fertilizer.getPrice(), 22);
    }

    @Test
    void testDecreasePrice() {
        fertilizer.decreasePrice(2);
        assertEquals(fertilizer.getPrice(), 8);
        fertilizer.decreasePrice(5);
        assertEquals(fertilizer.getPrice(), 3);
    }
}
