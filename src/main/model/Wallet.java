package model;

public class Wallet {
    private int walletBalance;

    // EFFECTS: set the wallet balance to zero at the begginning of the game
    public Wallet() {
        walletBalance = 0;
    }

    private int getBalance() {
         // stub
    }

    // REQUIRES: money >= 0
    // MODIFIES: this
    // EFFECTS: add specific amount of money into wallet
    private void earn(int money){
        // stub
    }

    // REQUIRES: money >=0 and money <= walletBalance
    // MODIFIES: this
    // EFFECTS: spend specific moeny from wallet
    private void spend(int money){
        //stub
    }

}
