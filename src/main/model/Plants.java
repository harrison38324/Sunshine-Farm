package model;

// Plants that can be planted and sold when it is mature
// Plants have 3 properties: 'name' 'price' 'growth Time'
// when 'growth Time' = 0, it can be sold with double price
// fertilizer can be applied to planted plants to reduce 'growth Time'
public class Plants extends AgriculturalEntity {
    // private String plantsName;
    // private int price;
    // private int growthTime;

    // REQUIRES: price>=0 , growthTime>=0 plantsName cannot be an empty string
    // EFFECTS: set a new kind of plants by assigned the plantsName
    // its price and the time it consumed to grow up
    public Plants(String name, int price, int growthTime) {
        super(name, price, growthTime);
    }

    // MODIFIES: this
    // EFFECTS: apply the fertilizer to the plants
    public void useFertilizer(Fertilizer fertilizer) {
        decreaseTime(fertilizer.getTime());
    }
}
