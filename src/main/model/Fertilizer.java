package model;

// the Fertilizer that used to reduce the growth time for Plants
// can only apply on plants in the plantsSlot(planted plants)
// have 3 property: 'name' 'price' 'time Reduced'
// 'time Reduced' means how much time it can reduced the growth time of Plants
public class Fertilizer extends AgriculturalEntity {

    // REQUIRES: price >= 0 ,TimeReduced >= 0, Name cannot be empty String
    // EFFECTS: set a new kind of Fertilizer by assigned the Name
    // its price and the time it reduces the plant to grow up
    public Fertilizer(String name, int price, int timeReduced) {
        super(name, price, timeReduced);
    }
}
