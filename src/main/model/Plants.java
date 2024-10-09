package model;

public class Plants {
    private String plantsName;
    private int price;
    private int growthTime;

    // REQUIRES: price>=0 , growthTime>=0 plantsName cannot be an empty string
    // EFFECTS: set a new kind of plants by assigned the plantsName
    //          its price and the time it consumed to grow up
    public Plants(String plantsName,int price,int growthTime){
        //stub
    }

    public int getPrice(){
        //stub
    }
     
    public int getPlantsName(){
        //stub
    }

    public int getGrowthTime(){
        //stub
    }

    // REQUIRES: money >= 0
    // MODIFIES: this
    // EFFECTS: increase the price of the plant by the input money
    public void increasePrice(int money){
        //stub
    }

    // REQUIRES: money >= 0
    // MODIFIES: this
    // EFFECTS: decrease the price of the plant by the input money
    public void decreasePrice(int money){
        //stub
    }

    // REQUIRES: time >= growthTime
    // MODIFIES: this
    // EFFECTS: decrease the growthTime by input time
    public void decreaseGrowthTime(int time){
        //stub
    }

    // MODIFIES: this
    // EFFECTS: apply the fertilizer to the plants
    public void useFertilizer(Fertilizer fertilizer){
        //stub
    }
}
