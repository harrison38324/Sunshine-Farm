package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestFertilizerStorage {
    private FertilizerStorage fertilizerStorage;
    private List<String> fertilizerNameList;
    private List<Fertilizer> fertilizerList1;
    private List<Fertilizer> fertilizerList2;
    private List<Fertilizer> fertilizerList3;
    private List<Fertilizer> fertilizerList4;
    private Fertilizer norFertilizer;
    private Fertilizer rareFertilizer;
    private Fertilizer legendFertilizer;

    @BeforeEach
    void runBefore() {
        fertilizerStorage = new FertilizerStorage();

        norFertilizer = new Fertilizer("Small bag fertilizer", 20, 10);
        rareFertilizer = new Fertilizer("Big bag fertilizer", 40, 25);
        legendFertilizer = new Fertilizer("Advanced big bag fertilizer", 200, 200);

        fertilizerList1 = new ArrayList<>();
        fertilizerList1.add(norFertilizer);

        fertilizerList2 = new ArrayList<>();
        fertilizerList2.add(norFertilizer);
        fertilizerList2.add(rareFertilizer);
        fertilizerList2.add(legendFertilizer);

        fertilizerList3 = new ArrayList<>();
        fertilizerList3.add(legendFertilizer);
        fertilizerList3.add(rareFertilizer);
        fertilizerList3.add(norFertilizer);

        fertilizerList4 = new ArrayList<>();
        fertilizerList4.add(norFertilizer);
        fertilizerList4.add(legendFertilizer);

        fertilizerNameList = new ArrayList<>();
        fertilizerNameList.add("Big bag fertilizer");
        fertilizerNameList.add("Small bag fertilizer");
        fertilizerNameList.add("Advanced big bag fertilizer");
    }

    @Test
    void testAddFertilizer() {
        fertilizerStorage.add(norFertilizer);
        fertilizerStorage.add(rareFertilizer);
        fertilizerStorage.add(legendFertilizer);
        assertEquals(fertilizerStorage.getStorage(), fertilizerList2);
    }

    @Test
    void testRemove() {
        fertilizerStorage.add(norFertilizer);
        fertilizerStorage.add(rareFertilizer);
        fertilizerStorage.add(legendFertilizer);
        assertEquals(fertilizerStorage.getStorage(), fertilizerList2);
        fertilizerStorage.remove(1);
        assertEquals(fertilizerStorage.getStorage(), fertilizerList4);
        fertilizerStorage.remove(1);
        assertEquals(fertilizerStorage.getStorage(), fertilizerList1);

    }

    @Test
    void testGetNameList() {
        fertilizerStorage.add(rareFertilizer);
        fertilizerStorage.add(norFertilizer);
        fertilizerStorage.add(legendFertilizer);
        assertEquals(fertilizerStorage.getNameList(), fertilizerNameList);
    }

    @Test
    void testGetFertilizerStorage() {
        fertilizerStorage.add(legendFertilizer);
        fertilizerStorage.add(rareFertilizer);
        fertilizerStorage.add(norFertilizer);
        assertEquals(fertilizerStorage.getStorage(), fertilizerList3);
    }

    @Test
    void testGetNameofFertilizeri() {
        fertilizerStorage.add(legendFertilizer);
        fertilizerStorage.add(rareFertilizer);
        fertilizerStorage.add(norFertilizer);
        assertEquals(fertilizerStorage.getNameofi(0), "Advanced big bag fertilizer");
        assertEquals(fertilizerStorage.getNameofi(1), "Big bag fertilizer");
        assertEquals(fertilizerStorage.getNameofi(2), "Small bag fertilizer");
    }

    @Test
    void testGetiFertilizer() {
        fertilizerStorage.add(legendFertilizer);
        fertilizerStorage.add(rareFertilizer);
        fertilizerStorage.add(norFertilizer);
        assertEquals(fertilizerStorage.geti(0), legendFertilizer);
        assertEquals(fertilizerStorage.geti(1), rareFertilizer);
        assertEquals(fertilizerStorage.geti(2), norFertilizer);
    }

    @Test
    void testbuyFertilizer(){
        fertilizerStorage.buyEntity(norFertilizer);
        assertFalse(fertilizerStorage.geti(0) == norFertilizer);
        assertEquals(fertilizerStorage.geti(0).getName(), norFertilizer.getName());
        assertEquals(fertilizerStorage.geti(0).getName(), norFertilizer.getName());
        assertEquals(fertilizerStorage.geti(0).getPrice(), norFertilizer.getPrice());
        fertilizerStorage.buyEntity(norFertilizer);
        assertFalse(fertilizerStorage.geti(1) == norFertilizer);
        assertEquals(fertilizerStorage.geti(1).getName(), norFertilizer.getName());
        assertEquals(fertilizerStorage.geti(1).getTime(), norFertilizer.getTime());
        assertEquals(fertilizerStorage.geti(1).getPrice(), norFertilizer.getPrice());
        fertilizerStorage.geti(1).decreasePrice(10);
        assertEquals(fertilizerStorage.geti(0).getPrice(), 20);
        assertEquals(fertilizerStorage.geti(1).getPrice(), 10);
    }
}
