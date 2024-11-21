package ui;

import javax.swing.*;

import model.AgriculturalEntity;
import model.AgriculturalEntityStorage;
import model.FertilizerStorage;

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
    private JLabel FarmMenuText;
    private CoreData coreData;

    // the main part of the FarmMenu Panel
    public FarmMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData) {
        this.coreData = coreData;
        setLayout(new CardLayout());

        FarmMenuText = new JLabel("This is your Sunshine Farm, what do you want to do");

        createButtons();
        addActionListeners(cardLayout, mainPanel);

        add(FarmMenuText);
        addButtonsToFarmMenu();
    }

    // MODIFIES: this
    // EFFECTS: create buttons needed for the Farm Menu
    private void createButtons() {
        goToShop = new JButton("Go to Shop");
        goToMainMenu = new JButton("Go to Main Menu");
        checkFertilizer = new JButton("Check Fertilizer Storage");
        checkPlantsStorage = new JButton("Check Plants Storage");
        checkSLotsStatus = new JButton("Check Slots Status");
        plantPlants = new JButton("Plant Plants");
        useFertilizer = new JButton("Use Fertilizer");
        sellMaturePlants = new JButton("Sell Mature Plants");
    }

    // MODIFISE: this
    // EFFECTS: create action listeners for the buttons in the Farm Menu
    private void addActionListeners(CardLayout cardLayout, JPanel mainPanel) {
        goToShop.addActionListener(e -> cardLayout.show(mainPanel, "Shop Menu"));
        goToMainMenu.addActionListener(e -> cardLayout.show(mainPanel, "Main Menu"));
        checkFertilizer.addActionListener(
                e -> FarmMenuText.setText(generateAriculturalEntityNameList(coreData.fertilizerStorage)));
        checkPlantsStorage.addActionListener(e -> FarmMenuText.setText(generateAriculturalEntityNameList(coreData.plantsStorage)));
        checkSLotsStatus.addActionListener(e -> FarmMenuText.setText(generateAriculturalEntityNameList(coreData.plantsSlot)));
        plantPlants.addActionListener(e->)
        useFertilizer
        sellMaturePlants
    }

    // EFFECTS: add buttons to the FarmMenu
    private void addButtonsToFarmMenu() {
        add(goToShop);
        add(checkFertilizer);
        add(checkPlantsStorage);
        add(checkSLotsStatus);
        add(plantPlants);
        add(useFertilizer);
        add(sellMaturePlants);
    }

    // EFFECTS: generate the FertilizerStorage Name List
    private String generateAriculturalEntityNameList(AgriculturalEntityStorage agriculturalEntityStorage) {
        String tempString = new String();
        tempString += "\n The Fertilizer you have:";
        for (String name : agriculturalEntityStorage.getNameList()) {
            tempString += name;
        }
        return tempString;
    }
}
