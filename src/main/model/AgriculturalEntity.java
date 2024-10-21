package model;

public class AgriculturalEntity {
    protected String name;
    protected int price;
    protected int time;

    // REQUIRES: price >= 0 ,time >= 0, Name cannot be empty String
    // EFFECTS: set a new kind of AgriculturalEntity by assigned the Name
    // its price and the time property
    public AgriculturalEntity(String name, int price, int time) {
        this.name = name;
        this.price = price;
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    // REQUIRES: money >= 0
    // MODIFIES: this
    // EFFECTS: increase price by the input money
    public void increasePrice(int money) {
        price += money;
    }

    // REQUIRES: money >= 0, money<=Price
    // MODIFIES: this
    // EFFECTS: decrease price by the input money
    public void decreasePrice(int money) {
        price -= money;
    }

    // REQUIRES: inputTime >= time
    // MODIFIES: this
    // EFFECTS: decrease the time property by input InputTime
    public void decreaseTime(int inputTime) {
        time -= inputTime;
    }

    // REQUIRES: inputTime >=0
    public void setTime(int inputTime) {
        this.time = inputTime;
    }
}
