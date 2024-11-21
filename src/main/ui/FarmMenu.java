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
    private JLabel farmMenuText;

    private PlantPlantsMenu plantPlantsMenu;
    private SelectFertilizerMenu selectFertilizerMenu;
    private ApplyFertilizerMenu applyFertilizerMenu;
    private SellMaturePlantMenu sellMaturePlantMenu;
    private CoreData coreData;

    // the main part of the FarmMenu Panel
    public FarmMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData, PlantPlantsMenu plantPlantsMenu,
            SelectFertilizerMenu selectFertilizerMenu, ApplyFertilizerMenu applyFertilizerMenu, SellMaturePlantMenu sellMaturePlantMenu) {
        this.plantPlantsMenu = plantPlantsMenu;
        this.selectFertilizerMenu = selectFertilizerMenu;
        this.applyFertilizerMenu = applyFertilizerMenu;
        this.sellMaturePlantMenu = sellMaturePlantMenu;
        this.coreData = coreData;
        setLayout(new FlowLayout());

        farmMenuText = new JLabel("This is your Sunshine Farm, what do you want to do");

        createButtons();
        addActionListeners(cardLayout, mainPanel);

        add(farmMenuText);
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
                e -> farmMenuText
                        .setText(generateAriculturalEntityNameList(coreData.fertilizerStorage, "Fertilizer Storage")));
        checkPlantsStorage.addActionListener(
                e -> farmMenuText.setText(generateAriculturalEntityNameList(coreData.plantsStorage, "Plants Storage")));
        checkSLotsStatus
                .addActionListener(e -> farmMenuText
                        .setText(generateAriculturalEntityNameList(coreData.plantsSlot, "Plant Slot")));
        plantPlants.addActionListener(e -> cardLayout.show(mainPanel, "Plant Plants Menu"));
        useFertilizer.addActionListener(e -> cardLayout.show(mainPanel, "Select Fertilizer Menu"));
        sellMaturePlants.addActionListener(e -> {
            sellMaturePlantMenu.refreshButtons();
            cardLayout.show(mainPanel, "Sell Mature Plant Menu");
        });
    }

    // EFFECTS: add buttons to the FarmMenu
    private void addButtonsToFarmMenu() {
        add(goToShop);
        add(goToMainMenu);
        add(checkFertilizer);
        add(checkPlantsStorage);
        add(checkSLotsStatus);
        add(plantPlants);
        add(useFertilizer);
        add(sellMaturePlants);
    }

    // EFFECTS: generate the FertilizerStorage Name List
    private String generateAriculturalEntityNameList(AgriculturalEntityStorage agriculturalEntityStorage, String name) {
        String tempString = new String();
        tempString += "\n The " + name + " status: ";
        for (String plantName : agriculturalEntityStorage.getNameList()) {
            tempString += plantName + " ";
        }
        return tempString;
    }
}
