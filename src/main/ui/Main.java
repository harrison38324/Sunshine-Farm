package ui;

import model.CoreData;

// Runing the RunProgram ui
public class Main {
    // EFFECTS: run the program
    public static void main(String[] args) throws Exception {
        CoreData coreData = new CoreData();
        System.out.println("Welcome to my project!");
        new FarmGameGUI(coreData);
    }
}
