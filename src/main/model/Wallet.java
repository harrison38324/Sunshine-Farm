package model;

// the amount of money you have
public class Wallet {
    private int walletBalance;

    // EFFECTS: set the wallet balance to zero at the begginning of the game
    public Wallet() {
        this.walletBalance = 0;
    }

    public int getBalance() {
        return walletBalance;
    }

    // REQUIRES: money >= 0
    // MODIFIES: this
    // EFFECTS: add specific amount of money into wallet
    public void earn(int money) {
        walletBalance += money;
    }

    // REQUIRES: money >=0 and money <= walletBalance
    // MODIFIES: this
    // EFFECTS: spend specific moeny from wallet
    public void spend(int money) {
        walletBalance -= money;
    }

}
