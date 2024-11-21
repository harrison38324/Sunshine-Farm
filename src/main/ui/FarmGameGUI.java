package ui;

import javax.swing.*;
import java.awt.*;

// Sunshine Farm Game user interface
public class FarmGameGUI extends JFrame {
    private JPanel mainMenu;
    private JPanel shopMenu;
    private PlantPlantsMenu plantPlantsMenu;
    private SelectFertilizerMenu selectFertilizerMenu;
    private ApplyFertilizerMenu applyFertilizerMenu;
    private SellMaturePlantMenu sellMaturePlantMenu;
    private JPanel farmMenu;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CoreData coreData;

    // constructor of the FarmGameGUI, initialize the GUI
    public FarmGameGUI(CoreData coreData) {
        this.coreData = coreData;

        setTitle("SunShine Farm");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
                applyFertilizerMenu, sellMaturePlantMenu);
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

        goToFarm.addActionListener(e -> cardLayout.show(mainPanel, "Farm Menu"));
        goToShop.addActionListener(e -> cardLayout.show(mainPanel, "Shop Menu"));

        JLabel mainMenuTextLabel = new JLabel("Hello,Sunshine Farm, what do you want to do today?");

        ImageIcon icon = new ImageIcon("./data/image/th.jpeg");

        Image image = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JLabel imageLabel = new JLabel(scaledIcon);

        mainMenuPanel.add(mainMenuTextLabel);
        mainMenuPanel.add(imageLabel);
        mainMenuPanel.add(goToFarm);
        mainMenuPanel.add(goToShop);

        return mainMenuPanel;
    }
}
