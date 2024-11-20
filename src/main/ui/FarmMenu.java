package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Farm Menu panel
public class FarmMenu extends JPanel {
    private JButton goToShop;
    private JButton goToMainMenu;
    private JButton checkFertilizer;
    private JButton checkPlantsStorage;
    private JButton checkSLotsStatus;
    private JButton plantPlants;
    private JButton useFertilizer;
    private JButton sellMaturePlants;
    private JLabel FarmMenuIntroText;

    // the main part of the FarmMenu Panel
    public FarmMenu(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new FlowLayout());

        FarmMenuIntroText = new JLabel("This is your Sunshine Farm, what do you want to do");

        createButtons();
        addActionListeners(cardLayout, mainPanel);

        add(FarmMenuIntroText);
        addButtonsToFarmMenu();
    }

    // MODIFIES: this
    // EFFECTS: create buttons needed for the Farm Menu
    private void createButtons() {
        goToShop = new JButton("Go to Shop");
        goToMainMenu = new JButton("Go to Main Menu");
        checkFertilizer = new JButton("Check Fertilizer");
        checkPlantsStorage = new JButton("Check Plants Storage");
        checkSLotsStatus = new JButton("Check Slots Status");
        plantPlants = new JButton("Plant Plants");
        useFertilizer = new JButton("Use Fertilizer");
        sellMaturePlants = new JButton("Sell Mature Plants");
    }

    // MODIFISE: this
    // EFFECTS: create action listeners for the buttons in the Farm Menu
    private void addActionListeners(CardLayout cardLayout,JPanel mainPanel){
        goToShop.addActionListener(e -> cardLayout.show(mainPanel, "Shop Menu"));
        goToMainMenu.addActionListener(e-> cardLayout.show(mainPanel,"Main Menu"));
        checkFertilizer.addActionListener(e -> printFertilizerStorageNameList());
        
    }
}
