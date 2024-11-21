package ui;

// Runing the RunProgram ui
public class Main {
    public static void main(String[] args) throws Exception {
        CoreData coreData = new CoreData();
        // System.out.println("Welcome to my project!");
        // new ConsoleUI(coreData);
        new FarmGameGUI(coreData);
    }
}
