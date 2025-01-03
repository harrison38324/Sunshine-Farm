package ui;

import javax.swing.*;

import model.CoreData;
import model.EventLog;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Sunshine Farm Game user interface
public class FarmGameGUI extends JFrame {
    private JPanel mainMenu;
    private ShopMenu shopMenu;
    private PlantPlantsMenu plantPlantsMenu;
    private SelectFertilizerMenu selectFertilizerMenu;
    private ApplyFertilizerMenu applyFertilizerMenu;
    private SellMaturePlantMenu sellMaturePlantMenu;
    private FarmMenu farmMenu;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CoreData coreData;

    private JLabel mainMenuTextLabel;

    // constructor of the FarmGameGUI, initialize the GUI
    public FarmGameGUI(CoreData coreData) {
        this.coreData = coreData;

        setTitle("SunShine Farm");
        setSize(600, 400);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());

                System.out.println("Log ended");
                dispose();
                System.exit(0);
            }
        });
        setUpMainPanel();

        setupJPanels();
        addToMainPanel();

        add(mainPanel);

        setVisible(true);
    }

    // EFFECTS: set up main panel
    private void setUpMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);
    }

    // EFFECTS: set up JPanels
    private void setupJPanels() {
        mainMenu = createMainMenu();
        shopMenu = new ShopMenu(cardLayout, mainPanel, coreData);
        plantPlantsMenu = new PlantPlantsMenu(cardLayout, mainPanel, coreData);
        selectFertilizerMenu = new SelectFertilizerMenu(cardLayout, mainPanel, coreData);
        applyFertilizerMenu = new ApplyFertilizerMenu(cardLayout, mainPanel, coreData);
        sellMaturePlantMenu = new SellMaturePlantMenu(cardLayout, mainPanel, coreData);
        farmMenu = new FarmMenu(cardLayout, mainPanel, coreData, plantPlantsMenu, selectFertilizerMenu,
                applyFertilizerMenu, sellMaturePlantMenu, shopMenu);
    }

    // MODIFIEES: this
    // EFFECTS: add set up JPanels to the main Panel
    private void addToMainPanel() {
        mainPanel.add(mainMenu, "Main Menu");
        mainPanel.add(farmMenu, "Farm Menu");
        mainPanel.add(shopMenu, "Shop Menu");
        mainPanel.add(plantPlantsMenu, "Plant Plants Menu");
        mainPanel.add(selectFertilizerMenu, "Select Fertilizer Menu");
        mainPanel.add(applyFertilizerMenu, "Apply Fertilizer Menu");
        mainPanel.add(sellMaturePlantMenu, "Sell Mature Plant Menu");

    }

    // EFFECTS: create Main Menu of the game
    private JPanel createMainMenu() {
        JPanel mainMenuPanel = new JPanel();

        mainMenuPanel.setLayout(new FlowLayout());

        JButton goToFarm = new JButton("Go to Farm");
        JButton goToShop = new JButton("Go to Shop");
        JButton saveData = new JButton("Save Data");
        JButton loadData = new JButton("Load Data");

        goToFarm.addActionListener(e -> cardLayout.show(mainPanel, "Farm Menu"));
        goToShop.addActionListener(e -> displayMenu(shopMenu, "Shop Menu"));
        saveData.addActionListener(e -> saveData());
        loadData.addActionListener(e -> loadData());

        mainMenuTextLabel = new JLabel("Hello,Sunshine Farm, what do you want to do today?");

        ImageIcon icon = new ImageIcon("./data/image/th.jpeg");

        Image image = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JLabel imageLabel = new JLabel(scaledIcon);

        mainMenuPanel.add(mainMenuTextLabel);
        mainMenuPanel.add(imageLabel);
        mainMenuPanel.add(goToFarm);
        mainMenuPanel.add(goToShop);
        mainMenuPanel.add(saveData);
        mainMenuPanel.add(loadData);

        return mainMenuPanel;
    }

    // MODIFIES: coreData
    // EFFECTS: load game data, and show feedback(success or not)
    private void loadData() {
        boolean isSucess = coreData.loadGameData();
        if (isSucess) {
            mainMenuTextLabel.setText("Load Sucessfully");
        } else {
            mainMenuTextLabel.setText("Error, fail to load game data");
        }
    }

    // MODIFIES: coreData
    // EFFECTS: save game data, and show feedback(success or not)
    private void saveData() {
        boolean isSucess = coreData.saveGameData();
        if (isSucess) {
            mainMenuTextLabel.setText("Save Sucessfully");
        } else {
            mainMenuTextLabel.setText("Error, fail to load game data");
        }
    }

    // MODIFES: helperPanel
    // EFFECTS: refresh the buttons in the menu and display it
    private void displayMenu(HelperPanel helperPanel, String constrain) {
        helperPanel.refreshStatus();
        cardLayout.show(mainPanel, constrain);
    }

    // EFFECTS: print the log
    private static void printLog(EventLog el) {
        for (model.Event next : el) {
            System.out.println(next.toString());
        }

    }
}
