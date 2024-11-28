package ui;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AgriculturalEntityStorage;
import model.CoreData;

// Farm Menu panel, user can select to go to shop, go to main menu, check fertilizer storage,
// check plants storage, check slots status. plant plants, use fertilizer, sell mature plants
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
    private ShopMenu shopMenu;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    // the main part of the FarmMenu Panel
    public FarmMenu(CardLayout cardLayout, JPanel mainPanel, CoreData coreData, PlantPlantsMenu plantPlantsMenu,
            SelectFertilizerMenu selectFertilizerMenu, ApplyFertilizerMenu applyFertilizerMenu,
            SellMaturePlantMenu sellMaturePlantMenu, ShopMenu shopMenu) {
        this.plantPlantsMenu = plantPlantsMenu;
        this.selectFertilizerMenu = selectFertilizerMenu;
        this.applyFertilizerMenu = applyFertilizerMenu;
        this.sellMaturePlantMenu = sellMaturePlantMenu;
        this.coreData = coreData;
        this.shopMenu = shopMenu;

        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setLayout(new FlowLayout());

        farmMenuText = new JLabel("This is your Sunshine Farm, what do you want to do");

        createButtons();
        addActionListeners();

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
    private void addActionListeners() {
        goToShop.addActionListener(e -> displayMenu(shopMenu, "Shop Menu"));
        goToMainMenu.addActionListener(e -> cardLayout.show(mainPanel, "Main Menu"));
        checkFertilizer.addActionListener(
                e -> farmMenuText
                        .setText(generateAriculturalEntityNameList(coreData.getFertilizerStorage(),
                                "Fertilizer Storage")));
        checkPlantsStorage.addActionListener(
                e -> farmMenuText
                        .setText(generateAriculturalEntityNameList(coreData.getPlantsStorage(), "Plants Storage")));
        checkSLotsStatus
                .addActionListener(e -> farmMenuText
                        .setText(generateAriculturalEntityNameList(coreData.getPlantsSlot(), "Plant Slot")));
        plantPlants.addActionListener(e -> displayMenu(plantPlantsMenu, "Plant Plants Menu"));
        useFertilizer.addActionListener(e -> {
            applyFertilizerMenu.refreshStatus();
            displayMenu(selectFertilizerMenu, "Select Fertilizer Menu");
        });
        sellMaturePlants.addActionListener(e -> displayMenu(sellMaturePlantMenu, "Sell Mature Plant Menu"));
    }

    // MODIFES: helperPanel
    // EFFECTS: refresh the buttons in the menu and display it
    public void displayMenu(HelperPanel helperPanel, String constrain) {
        helperPanel.refreshStatus();
        cardLayout.show(mainPanel, constrain);
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
