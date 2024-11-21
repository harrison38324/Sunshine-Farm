package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAgriculturalEntityStorage {
    private AgriculturalEntityStorage agriculturalEntityStorage;
    private List<AgriculturalEntity> agriculturalEntityArray;
    private List<AgriculturalEntity> emptyAgriculturalEntityArray;
    private AgriculturalEntity agriculturalEntity1;
    private AgriculturalEntity agriculturalEntity2;

    @BeforeEach
    void runBefore(){
        agriculturalEntity1 = new AgriculturalEntity("test1", 0, 0);
        agriculturalEntity2 = new AgriculturalEntity("test2", 10, 102);
        agriculturalEntityStorage = new AgriculturalEntityStorage();
        agriculturalEntityArray = new ArrayList<>();
    }

    @Test
    void testClear() {
        agriculturalEntityArray.add(agriculturalEntity1);
        agriculturalEntityArray.add(agriculturalEntity2);

        agriculturalEntityStorage.add(agriculturalEntity1);
        agriculturalEntityStorage.add(agriculturalEntity2);

        assertEquals(agriculturalEntityArray, agriculturalEntityStorage.getStorage());
        agriculturalEntityStorage.clear();
        emptyAgriculturalEntityArray = new ArrayList<>();
        assertEquals(emptyAgriculturalEntityArray, agriculturalEntityStorage.getStorage());

    }

    @Test
    void testRemoveAgriculturalEntity(){
        agriculturalEntityStorage.add(agriculturalEntity1);
        agriculturalEntityStorage.add(agriculturalEntity2);
        agriculturalEntityArray.add(agriculturalEntity2);

        agriculturalEntityStorage.remove(agriculturalEntity1);
        assertEquals(agriculturalEntityStorage.getStorage(),agriculturalEntityArray);
    }
    
}
