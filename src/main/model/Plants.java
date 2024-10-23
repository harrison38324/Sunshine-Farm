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

    // public int getPrice() {
    // return price;
    // }

    // public String getPlantsName() {
    // return plantsName;
    // }

    // public int getGrowthTime() {
    // return growthTime;
    // }

    // // REQUIRES: money >= 0
    // // MODIFIES: this
    // // EFFECTS: increase the price of the plant by the input money
    // public void increasePrice(int money) {
    // price += money;
    // }

    // // REQUIRES: money >= 0
    // // MODIFIES: this
    // // EFFECTS: decrease the price of the plant by the input money
    // public void decreasePrice(int money) {
    // price -= money;
    // }

    // // REQUIRES: time >= growthTime
    // // MODIFIES: this
    // // EFFECTS: decrease the growthTime by input time
    // public void decreaseGrowthTime(int time) {
    // growthTime -= time;
    // }

    // MODIFIES: this
    // EFFECTS: apply the fertilizer to the plants
    public void useFertilizer(Fertilizer fertilizer) {
        decreaseTime(fertilizer.getTime());
    }

    // // REQUIRES: time >=0
    // public void setGrowthTime(int time) {
    // this.growthTime = time;
    // }
}
