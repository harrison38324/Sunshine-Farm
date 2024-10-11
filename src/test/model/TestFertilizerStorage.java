package model;

import static org.junit.Assert.assertEquals;

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
        fertilizerStorage.addFertilizer(norFertilizer);
        fertilizerStorage.addFertilizer(rareFertilizer);
        fertilizerStorage.addFertilizer(legendFertilizer);
        assertEquals(fertilizerStorage.getFertilizerStorage(), fertilizerList2);
    }

    @Test
    void testRemove() {
        fertilizerStorage.addFertilizer(norFertilizer);
        fertilizerStorage.addFertilizer(rareFertilizer);
        fertilizerStorage.addFertilizer(legendFertilizer);
        assertEquals(fertilizerStorage.getFertilizerStorage(), fertilizerList2);
        fertilizerStorage.remove(1);
        assertEquals(fertilizerStorage.getFertilizerStorage(),fertilizerList4);
        fertilizerStorage.remove(1);
        assertEquals(fertilizerStorage.getFertilizerStorage(), fertilizerList1);
        
    }

    @Test
    void testGetNameList() {
        fertilizerStorage.addFertilizer(rareFertilizer);
        fertilizerStorage.addFertilizer(norFertilizer);
        fertilizerStorage.addFertilizer(legendFertilizer);
        assertEquals(fertilizerStorage.getNameList(), fertilizerNameList);
    }

    @Test
    void testGetFertilizerStorage() {
        fertilizerStorage.addFertilizer(legendFertilizer);
        fertilizerStorage.addFertilizer(rareFertilizer);
        fertilizerStorage.addFertilizer(norFertilizer);
        assertEquals(fertilizerStorage.getFertilizerStorage(), fertilizerList3);
    }
}
