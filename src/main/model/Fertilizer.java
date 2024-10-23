package model;

public class Fertilizer extends AgriculturalEntity {
    // private String name;
    // private int price;
    // private int timeReduced;

    // REQUIRES: price >= 0 ,TimeReduced >= 0, Name cannot be empty String
    // EFFECTS: set a new kind of Fertilizer by assigned the Name
    // its price and the time it reduces the plant to grow up
    public Fertilizer(String name, int price, int timeReduced) {
        super(name, price, timeReduced);
    }

    // public int getPrice() {
    // return price;
    // }

    // public String getName() {
    // return name;
    // }

    // public int getTimeReduced() {
    // return timeReduced;
    // }

    // // REQUIRES: money >= 0
    // // MODIFIES: this
    // // EFFECTS: increase price by the input money
    // public void increasePrice(int money) {
    // price += money;
    // }

    // // REQUIRES: money >= 0, money<=Price
    // // MODIFIES: this
    // // EFFECTS: decrease price by the input money
    // public void decreasePrice(int money) {
    // price -= money;
    // }

}
