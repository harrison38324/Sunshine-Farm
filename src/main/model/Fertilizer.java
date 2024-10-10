package model;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.StringCache;

public class Fertilizer {
    private String Name;
    private int price;
    private int TimeReduced;

    // REQUIRES: price >= 0 ,TimeReduced >= 0, Name cannot be empty String
    // EFFECTS: set a new kind of Fertilizer by assigned the Name
    //          its price and the time it reduces the plant to grow up
    public Fertilizer(String Name,int price,int TimeReduced){
        this.Name = Name;
        this.price = price;
        this.TimeReduced = TimeReduced;
    }

    public int getPrice(){
        return price;
    }
     
    public String getName(){
        return Name;
    }

    public int getTimeReduced(){
        return TimeReduced;
    }

    // REQUIRES: money >= 0
    // MODIFIES: this
    // EFFECTS: increase price by the input money
    public void increasePrice(int money){
        price += money;
    }

    // REQUIRES: money >= 0, money<=Price
    // MODIFIES: this
    // EFFECTS: decrease price by the input money
    public void decreasePrice(int money){
        price -= money;
    }


}
